package java16.restoran.service.impl;

import jakarta.transaction.Transactional;
import java16.restoran.dto.request.MenuItemsRequest;
import java16.restoran.dto.response.MenuResponse;
import java16.restoran.entity.MenuItem;
import java16.restoran.entity.Restaurant;
import java16.restoran.entity.SubCategory;
import java16.restoran.exceptions.NotFount;
import java16.restoran.repo.MenuItemRepo;
import java16.restoran.repo.RestaurantRepo;
import java16.restoran.repo.SubCategoryRepo;
import java16.restoran.service.ImageService;
import java16.restoran.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuItemImpl implements MenuItemService {
    private final SubCategoryRepo subCategoryRepo;
    private final ImageService imageService;
    private final MenuItemRepo menuItemRepo;
    private final RestaurantRepo restaurantRepo;

    @Override
    public ResponseEntity<?> save(MenuItemsRequest menuItem, MultipartFile file) throws IOException {
        Restaurant restaurant = restaurantRepo
                .findRestaurantByException(menuItem.getRestaurantId());
        if (menuItem.getPrice().intValue() <= 0) {
            throw new NotFount("Price must be greater than 0");
        }
        String fileName = imageService.save(file);
        SubCategory subCategory = subCategoryRepo
                .findById(menuItem.getSubcategoryId()).orElse(null);
        MenuItem res = new MenuItem();
        res.setSubCategory(subCategory);
        assert subCategory != null;
        subCategory.getMenuItemList().add(res);
        subCategoryRepo.save(subCategory);
        res.setRestaurant(restaurant);
        res.setName(menuItem.getName());
        res.setDescription(menuItem.getDescription());
        res.setPrice(menuItem.getPrice());
        res.setIsVegetarian(menuItem.getIsVegetarian());
        res.setImage(fileName);
        MenuItem save = menuItemRepo.save(res);
        restaurant.getMenuItems().add(save);
        restaurantRepo.save(restaurant);
        return ResponseEntity.ok().body("success save");
    }

    @Override
    public MenuResponse getMenuItemById(Long id) {
        MenuItem menuItem = menuItemRepo.findById(id).orElseThrow(()
                -> new NotFount("Menu item not found by id " + id));
        return getMenuResponse(menuItem);
    }

    private MenuResponse getMenuResponse(MenuItem menuItem) {
        MenuResponse menuResponse = new MenuResponse();
        menuResponse.setName(menuItem.getName());
        menuResponse.setDescription(menuItem.getDescription());
        menuResponse.setPrice(menuItem.getPrice());
        menuResponse.setIsVegetarian(menuItem.getIsVegetarian());
        menuResponse.setImage(menuItem.getImage());
        menuResponse.setId(menuItem.getId());
        return menuResponse;
    }

    @Override
    public List<MenuResponse> search(String query) {
      return menuItemRepo.searchMenuItems(query).stream()
                .map(this::getMenuResponse).toList();

    }

    @Override
    public List<MenuResponse> getAllSortByPrice(String acsOrDesc) {
        if (acsOrDesc.equals("asc")) {
      return menuItemRepo.getAllSortByPrice().stream()
              .map(this::getMenuResponse).toList();
       }
        else if (acsOrDesc.equals("desc")) {
            return menuItemRepo.getAllSortByPriceDESC().stream()
                    .map(this::getMenuResponse).toList();
        }
        throw new NotFount("No such menu item >>>" + acsOrDesc + "<<< found");

    }

    @Override
    public List<MenuResponse> getAllFilterByVegetarian(boolean isTrue) {
      return menuItemRepo.findAllVegetarian(isTrue).stream()
              .map(this::getMenuResponse).toList();

    }
}
