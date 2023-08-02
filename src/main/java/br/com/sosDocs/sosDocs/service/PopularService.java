package br.com.sosDocs.sosDocs.service;

import br.com.sosDocs.sosDocs.entity.Marca;
import br.com.sosDocs.sosDocs.entity.Patrimonio;
import br.com.sosDocs.sosDocs.repository.MarcaRepository;
import br.com.sosDocs.sosDocs.repository.PatrimonioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PopularService {
    @Autowired
    private PatrimonioRepository patrimonioRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    public void popularBanco() {
        Marca marca = new Marca();
        marca.setNome("Marca1");

        Marca marca2 = new Marca();
        marca2.setNome("Marca2");

        Patrimonio patrimonio = new Patrimonio();
        patrimonio.setNome("Patrimonio1");
        patrimonio.setDescricao("descriçao1");
        patrimonio.setNumeroTombo("1");

        Patrimonio patrimonio2 = new Patrimonio();
        patrimonio2.setNome("Patrimonio2");
        patrimonio2.setDescricao("descriçao2");
        patrimonio2.setNumeroTombo("2");

        Patrimonio patrimonio3 = new Patrimonio();
        patrimonio3.setNome("Patrimonio3");
        patrimonio3.setDescricao("descriçao3");
        patrimonio3.setNumeroTombo("3");

        patrimonioRepository.save(patrimonio);
        patrimonioRepository.save(patrimonio2);
        patrimonioRepository.save(patrimonio3);

        List<Patrimonio> patrimonioList1 = new ArrayList<>();
        patrimonioList1.add(patrimonio);
        patrimonioList1.add(patrimonio2);

        List<Patrimonio> patrimonioList2 = new ArrayList<>();
        patrimonioList2.add(patrimonio3);

        marca.setPatrimonios(patrimonioList1);
        marca2.setPatrimonios(patrimonioList2);

        marcaRepository.save(marca);
        marcaRepository.save(marca2);

    }

}
