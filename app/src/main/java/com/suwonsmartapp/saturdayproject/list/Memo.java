package com.suwonsmartapp.saturdayproject.list;

import java.io.Serializable;

/**
 * Created by junsuk on 16. 8. 20..
 */
public class Memo implements Serializable {
    private String title;
    private String contents;

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Memo{");
        sb.append("contents='").append(contents).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append('}');
        return sb.toString();
    }


}
