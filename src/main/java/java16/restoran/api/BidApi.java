package java16.restoran.api;

import jakarta.validation.Valid;
import java16.restoran.dto.request.BidRequest;
import java16.restoran.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bid")
public class BidApi {
    private final BidService bidService;

    //save
    @PostMapping("/saveBid/{id}")
    public String saveBid(@RequestBody @Valid BidRequest bidRequest,
                          @PathVariable Long id) {
        return bidService.saveBid(bidRequest, id);
    }

    //add employ
    @PostMapping("/saveEmployee/{id}/{restaurantId}")
    public String saveEmployee(@PathVariable Long id,
                               @RequestParam String add,
                               @PathVariable Long restaurantId) {
        return bidService.addEmployee(id,add,restaurantId);
    }

}
