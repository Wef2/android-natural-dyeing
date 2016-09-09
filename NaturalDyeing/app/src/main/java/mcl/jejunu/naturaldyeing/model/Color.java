package mcl.jejunu.naturaldyeing.model;

/**
 * Created by Kim on 2016-07-25.
 */
public class Color {

    private Long id;
    private String name;
    private String rgb;
    private String lab;
    private String hvc;
    private String hexhtml;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRgb() {
        return rgb;
    }

    public void setRgb(String rgb) {
        this.rgb = rgb;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public String getHvc() {
        return hvc;
    }

    public void setHvc(String hvc) {
        this.hvc = hvc;
    }

    public String getHexhtml() {
        return hexhtml;
    }

    public void setHexhtml(String hexhtml) {
        this.hexhtml = hexhtml;
    }
}
