package carmenromano.capstone_project.services;


import carmenromano.capstone_project.entities.Comune;
import carmenromano.capstone_project.entities.Product;
import carmenromano.capstone_project.entities.Provincia;
import carmenromano.capstone_project.repositories.ComuneRepository;
import carmenromano.capstone_project.repositories.ProductRepository;
import carmenromano.capstone_project.repositories.ProvinciaRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;
@Service
public class ImportService {

    @Autowired
    private ComuneRepository comuneRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @PostConstruct
    public void init() {
      // importProvince();
   //  importComuni();
    }

    public void importProvince() {
        try (Reader reader = new FileReader("gi_province.csv")) {
            List<Provincia> province = new CsvToBeanBuilder<Provincia>(reader)
                    .withType(Provincia.class)
                    .withSeparator(';')
                    .withSkipLines(1)
                    .build()
                    .parse();

            provinciaRepository.saveAll(province);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void importComuni() {
        try (Reader reader = new FileReader("gi_comuni.csv")) {
            CsvToBean<Comune> csvToBean = new CsvToBeanBuilder<Comune>(reader)
                    .withType(Comune.class)
                    .withSeparator(';')
                    .withSkipLines(1)
                    .build();

            List<Comune> comuni = csvToBean.parse();
            comuneRepository.saveAll(comuni);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

