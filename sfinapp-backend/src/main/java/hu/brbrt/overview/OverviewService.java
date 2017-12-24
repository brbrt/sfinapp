package hu.brbrt.overview;

import hu.brbrt.core.TransactionType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OverviewService {

    private final OverviewRepository overviewRepository;

    public OverviewService(OverviewRepository overviewRepository) {
        this.overviewRepository = overviewRepository;
    }

    public List<OverviewItem> get() {
        return overviewRepository.get()
                .stream()
                .map(this::postProcess)
                .collect(Collectors.toList());
    }

    private OverviewItem postProcess(OverviewItem ov) {
        return ov.setType(TransactionType.calculate(ov.getToAccountName(), ov.getAmount()));
    }

}
