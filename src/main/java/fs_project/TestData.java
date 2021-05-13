package fs_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestData {
    private final boolean TESTDATA_ENABLED = true;



    @PostConstruct
    private void postConstruct() {
        if (!TESTDATA_ENABLED) return;

    }
}
