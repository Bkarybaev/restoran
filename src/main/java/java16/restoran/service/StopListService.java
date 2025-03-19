package java16.restoran.service;

public interface StopListService {
    String save(Long menuId,String reason);

    String delete(Long menuId);
}
