package br.com.jessica.meu_lanchinho.services;

import br.com.jessica.meu_lanchinho.model.*;
import br.com.jessica.meu_lanchinho.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService {
    private final CompraRepository compraRepository;
    private final PedidoRepository pedidoRepository;
    private final LancheRepository lancheRepository;
    private final IngredienteRepository ingredienteRepository;
    private final PromocaoRepository promocaoRepository;

    public CompraService(CompraRepository compraRepository, PedidoRepository pedidoRepository, LancheRepository lancheRepository, IngredienteRepository ingredienteRepository,
                         PromocaoRepository promocaoRepository){
        this.compraRepository = compraRepository;
        this.pedidoRepository = pedidoRepository;
        this.lancheRepository = lancheRepository;
        this.ingredienteRepository = ingredienteRepository;
        this.promocaoRepository = promocaoRepository;
    }

    private List<Ingrediente> obtemIngrediente(Pedido pedido){
        List<Ingrediente> ingredientes = new ArrayList<>();
        if(pedido.getLanche() != null){
            ingredientes.addAll(pedido.getLanche().getIngrediente());
        }
        if (pedido.getIngredientesExtras() != null){
            ingredientes.addAll(pedido.getIngredientesExtras());
        }
        return ingredientes;
    }

    private Double calculaPreco(List<Ingrediente> ingredientes){
        return ingredientes.stream().map(Ingrediente::getPreco).reduce(0D, Double::sum);
    }

    private Optional<Promocao> calculaPromocao(List<Ingrediente> ingredientes){
        long quantidadeCarne = ingredientes.stream().filter(ingrediente -> ingrediente.getNome().equals(EIngrediente.HAMBURGUER.getIngrediente().getNome())).count();
        long quantidadeQueijo = ingredientes.stream().filter(ingrediente -> ingrediente.getNome().equals(EIngrediente.QUEIJO.getIngrediente().getNome())).count();
        if(ingredientes.stream().anyMatch(ingrediente -> ingrediente.getNome().equals(EIngrediente.ALFACE.getIngrediente().getNome())) &&
           ingredientes.stream().noneMatch(ingrediente -> ingrediente.getNome().equals(EIngrediente.BACON.getIngrediente().getNome()))){
            Promocao promocao = new Promocao(EPromocao.LIGHT.getPromocao());
            Double valorTotal = calculaPreco(ingredientes);
            promocao.setValorDesconto(valorTotal*0.10);
            return Optional.of(promocao);
        } else if(quantidadeCarne >= 3){
            double precoCarne = ingredientes.stream().filter(ingrediente -> ingrediente.getNome().equals(EIngrediente.HAMBURGUER.getIngrediente().getNome())).findAny().orElseThrow().getPreco();
            Promocao promocao = new Promocao(EPromocao.MUITA_CARNE.getPromocao());
            promocao.setValorDesconto(precoCarne*0.33);
            return Optional.of(promocao);
        } else if(quantidadeQueijo >= 3){
            double precoQueijo = ingredientes.stream().filter(ingrediente -> ingrediente.getNome().equals(EIngrediente.QUEIJO.getIngrediente().getNome())).findAny().orElseThrow().getPreco();
            Promocao promocao = new Promocao(EPromocao.MUITO_QUEIJO.getPromocao());
            promocao.setValorDesconto(precoQueijo*0.33);
            return Optional.of(promocao);
        }
        return Optional.empty();
    }

    private void processaPedidos(List<Pedido> pedidos, String usuario){
        for(Pedido pedido : pedidos){
            pedido.setUsuario(usuario);
            atualizaValoresPedidos(pedido);
            List<Ingrediente> ingredientes = obtemIngrediente(pedido);
            pedido.setPreco(calculaPreco(ingredientes));
            calculaPromocao(ingredientes).ifPresentOrElse(promocao -> {
                pedido.setPromocao(promocao);
                pedido.setPrecoFinal(pedido.getPreco() - promocao.getValorDesconto());
                promocaoRepository.save(promocao);
            }, () -> pedido.setPrecoFinal(pedido.getPreco()));
            pedidoRepository.save(pedido);
        }
    }

    private void atualizaValoresPedidos(Pedido pedido) {
        pedido.setLanche(ELanche.values()[pedido.getLanche().getId().intValue()].getLanche());
        ingredienteRepository.saveAll(pedido.getLanche().getIngrediente());
        lancheRepository.save(pedido.getLanche());
        List<Ingrediente> ingredientes = new ArrayList<>();
        if(pedido.getIngredientesExtras() == null)
            return;
        for(Ingrediente ingrediente : pedido.getIngredientesExtras()){
            ingredientes.add(EIngrediente.values()[ingrediente.getId().intValue()].getIngrediente());
        }
        ingredienteRepository.saveAll(pedido.getIngredientesExtras());
        pedido.setIngredientesExtras(ingredientes);
    }

    private double calculaPrecoCompra(List<Pedido> pedidos){
        return pedidos.stream().map(Pedido::getPrecoFinal).reduce(0D, Double::sum);
    }

    public ResponseEntity<Compra> comprar(List<Pedido> pedidos) {
        Compra compra = new Compra();
        String usuario = "adm";
        processaPedidos(pedidos, usuario);
        pedidoRepository.saveAll(pedidos);
        compra.setPedidos(pedidos);
        compra.setPreco(calculaPrecoCompra(pedidos));
        compraRepository.save(compra);
        return ResponseEntity.ok(compra);
    }

    public ResponseEntity<Page<Compra>> getAll(Pageable pageable) {
        return ResponseEntity.ok(compraRepository.findAll(pageable));
    }

    public ResponseEntity<Compra> getById(Long id) {
        Optional<Compra> compraEncontrada = compraRepository.findById(id);
        return compraEncontrada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
