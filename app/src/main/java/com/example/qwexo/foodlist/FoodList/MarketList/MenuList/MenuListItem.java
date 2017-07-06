package com.example.qwexo.foodlist.FoodList.MarketList.MenuList;

/**
 * Created by qwexo on 2017-06-22.
 */

public class MenuListItem {
    private String menuName;
    private String menuPrice;
    private String menuUserId;
    private String menuId;

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuUserId(String menuUserId) {
        this.menuUserId = menuUserId;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getMenuUserId() {
        return menuUserId;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getMenuPrice() {
        return menuPrice;
    }
}
