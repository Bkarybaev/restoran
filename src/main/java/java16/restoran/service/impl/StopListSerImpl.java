package java16.restoran.service.impl;

import jakarta.transaction.Transactional;
import java16.restoran.entity.MenuItem;
import java16.restoran.entity.StopList;
import java16.restoran.exceptions.NotFount;
import java16.restoran.repo.MenuItemRepo;
import java16.restoran.repo.StopRepo;
import java16.restoran.service.StopListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class StopListSerImpl implements StopListService {
    private final StopRepo stopRepo;
    private final MenuItemRepo menuItemRepo;

    @Override
    public String save(Long menuId, String reason) {
        MenuItem item = menuItemRepo.findByIdException(menuId);
        StopList stopList = new StopList();
        stopList.setReason(reason);
        stopList.setMenuItem(item);

        if (item.getStopList() != null) {
            throw new NotFount("save stop list");
        }
        item.setStopList(stopList);
        menuItemRepo.save(item);
        stopRepo.save(stopList);
        return "success save";
    }

    @Override
    public String delete(Long menuId) {
        MenuItem menuItem =stopRepo.findByIdMenuItem(menuId);
        if(menuItem == null) {
            throw new NotFount("menu item not found for id " + menuId);
        }
        Long stopList = menuItem.getStopList().getId();
        menuItem.setStopList(null);
        menuItemRepo.save(menuItem);
        stopRepo.deleteById(stopList);
        return "success delete";
    }
}
