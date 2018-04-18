package vn.com.codedao.facecook.presenter.menu;

import java.util.ArrayList;
import java.util.List;

import vn.com.codedao.facecook.model.menu.MMenu;
import vn.com.codedao.facecook.view.menu.IFragmentMenu;

public class PresenterLogicHandleMenu implements IPresenterLogicHandleMenu {
    private IFragmentMenu mIFragmentMenu;

    public PresenterLogicHandleMenu(IFragmentMenu mIFragmentMenu) {
        this.mIFragmentMenu = mIFragmentMenu;
    }


    @Override
    public void getListMenu() {
        List<MMenu> mMenus = new ArrayList<>();
     mMenus.add(new MMenu("Hoàng Văn Nam"));
     mMenus.add(new MMenu("Bạn Bè"));
     mMenus.add(new MMenu("Gần Đây"));
     mMenus.add(new MMenu("Setting"));
     mMenus.add(new MMenu("Trợ giúp"));
     mMenus.add(new MMenu("Logout"));
        mIFragmentMenu.setApdater(mMenus);
    }
}
