package pizzashop.repository;

import pizzashop.model.MenuDataModel;

import java.util.List;

public interface IMenuRepo {
    List<MenuDataModel> getMenu();

}
