package com.exmple.www.finalmycollage;

import java.util.ArrayList;
import java.util.List;

public class parsdata {
    private String NameAPleca;
    private String NameLectuere;
    private String NameTask;
    private String TimeTask;
    private String _id;
    private List<String> arraychiled;

    public parsdata() {
        this.arraychiled = new ArrayList();
    }

    public String get_id() {
        return this._id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNameTask() {
        return this.NameTask;
    }

    public void setNameTask(String nameTask) {
        this.NameTask = nameTask;
        this.arraychiled.add("\u0627\u0644\u0645\u0633\u0627\u0642 : " + nameTask);
    }

    public String getNameLectuere() {
        return this.NameLectuere;
    }

    public void setNameLectuere(String nameLectuere) {
        this.NameLectuere = nameLectuere;
        this.arraychiled.add("\u0645\u062f\u0631\u0633 \u0627\u0644\u0645\u0633\u0627\u0642 : " + nameLectuere);
    }

    public String getNameAPleca() {
        return this.NameAPleca;
    }

    public void setNameAPleca(String nameAPleca) {
        this.NameAPleca = nameAPleca;
        this.arraychiled.add("\u0631\u0642\u0645 \u0627\u0644\u0642\u0627\u0639\u0629 : " + nameAPleca);
    }

    public String getTimeTask() {
        String chr = BuildConfig.FLAVOR;
        String time = this.TimeTask;
        for (int i = 0; i < 2; i++) {
            chr = chr + String.valueOf(time.charAt(i));
        }
        return time + (Integer.parseInt(chr) < 12 ? "am" : "pm");
    }

    public void setTimeTask(String timeTask) {
        this.TimeTask = timeTask;
        this.arraychiled.add("\u0645\u0648\u0639\u062f \u0627\u0644\u0645\u062d\u0627\u0636\u0631\u0629 : " + getTimeTask());
    }

    public List<String> getlist() {
        return this.arraychiled;
    }
}
