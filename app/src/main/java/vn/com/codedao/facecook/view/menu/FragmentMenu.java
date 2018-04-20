package vn.com.codedao.facecook.view.menu;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import vn.com.codedao.facecook.R;
import vn.com.codedao.facecook.model.menu.MMenu;
import vn.com.codedao.facecook.presenter.menu.PresenterLogicHandleMenu;
import vn.com.codedao.facecook.utils.Constant;
import vn.com.codedao.facecook.view.login.Login;
import vn.com.codedao.facecook.view.newfeed.SpacesItemDecoration;
import vn.com.codedao.facecook.view.updateuser.UpdateUserActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMenu extends Fragment implements IFragmentMenu, IOnClickItemMenu {


    public FragmentMenu() {
        // Required empty public constructor
    }

    private PresenterLogicHandleMenu mIPresenterLogicHandleMenu;
    private RecyclerView rcMenu;
    private MenuAdapter mMenuAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        // Inflate the layout for this fragment
        rcMenu = view.findViewById(R.id.rcMenu);
        mIPresenterLogicHandleMenu = new PresenterLogicHandleMenu(this);
        mIPresenterLogicHandleMenu.getListMenu();
        return view;
    }


    @Override
    public void setApdater(List<MMenu> menus) {
        mMenuAdapter = new MenuAdapter(menus, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rcMenu.setLayoutManager(mLayoutManager);
        rcMenu.addItemDecoration(new SpacesItemDecoration(1));
        rcMenu.setItemAnimator(new DefaultItemAnimator());
        rcMenu.setAdapter(mMenuAdapter);
        mMenuAdapter.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClickItemMenu(int position) {
        //TODO ItemClick
        switch (position) {
            case 0:
                Intent trantisionUpdate = new Intent(getActivity(), UpdateUserActivity.class);
                startActivity(trantisionUpdate);
                Toast.makeText(getContext(), "Trang cá nhân", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(getContext(), "bạn bè", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getContext(), "gần đây", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(getContext(), "setting", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(getContext(), "Trợ giúp", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                SharedPreferences prefs = getActivity()
                        .getSharedPreferences(Constant.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit().clear();
                editor.commit();
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
                Toast.makeText(getContext(), "LogOut", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
