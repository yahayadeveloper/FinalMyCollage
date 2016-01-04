package com.exmple.www.finalmycollage;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NativeFragment extends Fragment {
    List<parsdata> arrayList;
    Cursor f10c;
    CustumExpndaple custumExpndaple;
    int day;
    DBSqliteApp dbSqliteApp;
    ExpandableListView expandableListView;
    HashMap<String, List<String>> listChildData;
    int positions;
    Snackbar snackbar;

    /* renamed from: com.wisleem.mycollage.NativeFragment.1 */
    class C02061 implements OnItemLongClickListener {
        C02061() {
        }

        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
            NativeFragment.this.positions = position;
            return false;
        }
    }

    public NativeFragment(int day) {
        this.day = day;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.saturday_fragment, container, false);
        this.arrayList = new ArrayList();
        this.listChildData = new HashMap();
        this.dbSqliteApp = new DBSqliteApp(getActivity());
        this.expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        registerForContextMenu(this.expandableListView);
        this.custumExpndaple = new CustumExpndaple(getActivity(), this.arrayList, this.listChildData);
        this.expandableListView.setAdapter(this.custumExpndaple);
        this.expandableListView.setOnItemLongClickListener(new C02061());
        return view;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.option_ex, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete /*2131558545*/:
                try {
                    if (this.dbSqliteApp.deleteitem(((parsdata) this.expandableListView.getItemAtPosition(this.positions)).get_id())) {
                        onResume();
                        getsnackbar("تم حذف المهمة بنجاح").show();
                        break;
                    }
                } catch (Exception e) {
                    break;
                }
                break;
            case R.id.update /*2131558546*/:
                try {
                    parsdata oo = (parsdata) this.expandableListView.getItemAtPosition(this.positions);
                    Intent intent = new Intent(getActivity(), AddActivity.class);
                    intent.putExtra(DBSqliteApp.NAMETASK, oo.getNameTask());
                    intent.putExtra("nameLectuere", oo.getNameLectuere());
                    intent.putExtra("nameAPleca", oo.getNameAPleca());
                    intent.putExtra("timeTask", oo.getTimeTask());
                    intent.putExtra("dayindex", this.day);
                    getActivity().startActivity(intent);
                    break;
                } catch (Exception e2) {
                    break;
                }
        }
        return super.onContextItemSelected(item);
    }

    private Snackbar getsnackbar(String text) {
        if (this.snackbar == null) {
            this.snackbar = Snackbar.make(getActivity().findViewById(android.support.design.R.id.snackbar_text), (CharSequence) text, Snackbar.LENGTH_LONG);
        }
        return this.snackbar;
    }

    public void onResume() {
        super.onResume();
        this.f10c = null;
        if (this.arrayList.size() > 0 && this.listChildData.size() > 0) {
            this.arrayList.clear();
            for (int i = 0; i < this.arrayList.size(); i++) {
                ((List) this.listChildData.get(((parsdata) this.arrayList.get(i)).get_id())).clear();
            }
        }
        getitem();
        this.custumExpndaple.notifyDataSetChanged();
        this.expandableListView.setAdapter(this.custumExpndaple);
    }

    private void getitem() {
        if (this.f10c == null) {
            this.f10c = this.dbSqliteApp.getdalytaskdb(this.day);
            while (this.f10c.moveToNext()) {
                parsdata pp = new parsdata();
                pp.set_id(this.f10c.getString(this.f10c.getColumnIndex(DBSqliteApp._IDTASK)));
                pp.setNameTask(this.f10c.getString(this.f10c.getColumnIndex(DBSqliteApp.NAMETASK)));
                pp.setNameLectuere(this.f10c.getString(this.f10c.getColumnIndex(DBSqliteApp.NAMETHALECTURER)));
                pp.setNameAPleca(this.f10c.getString(this.f10c.getColumnIndex(DBSqliteApp.HALLTASK)));
                pp.setTimeTask(this.f10c.getString(this.f10c.getColumnIndex(DBSqliteApp.TIMETASK)));
                this.arrayList.add(pp);
                this.listChildData.put(pp.get_id(), pp.getlist());
            }
            this.f10c.close();
        }
    }
}
