package java16.restoran.service;

import jakarta.validation.Valid;
import java16.restoran.dto.request.BidRequest;

public interface BidService {
    String saveBid(@Valid BidRequest bidRequest, Long id);

    String addEmployee(Long id, String add,Long restaurantId);
}
