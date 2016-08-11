package mcl.jejunu.naturaldyeing.application;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import mcl.jejunu.naturaldyeing.model.Color;
import mcl.jejunu.naturaldyeing.model.Fabric;
import mcl.jejunu.naturaldyeing.model.Product;
import mcl.jejunu.naturaldyeing.model.Resource;

/**
 * Created by neo-202 on 2016-08-11.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.deleteRealm(config);
        Realm.setDefaultConfiguration(config);

        initData();
    }

    public void initData() {

        Realm realm = Realm.getDefaultInstance();

        Long id = Long.valueOf(1);

        realm.beginTransaction();
        Resource resource = new Resource();
        resource.setId(id);
        resource.setName("감태");
        resource.setScientificName("Ecklonia cava");
        resource.setDescription("미역과의 갈조류 제주도를 비롯한 우리나라 남해안에 자생하며 항산화성, 미백, 항노화 기능성이 보고됨");
        realm.copyToRealm(resource);

        Color color = new Color();
        color.setId(id);
        color.setName("Antique Bronze");
        color.setLab("45.3 21.3 12.8");
        color.setHvc("2.8YR 2/3.4");
        color.setRgb("147 130 95");
        color.setHexhtml("948260");
        realm.copyToRealm(color);

        Fabric fabric = new Fabric();
        fabric.setId(id);
        fabric.setName("순면 새틴 원단");
        fabric.setDescription("cotton 100%, sateen");
        realm.copyToRealm(fabric);

        Product product = new Product();
        product.setId(id);
        product.setName("와이드 슬리브 자켓");
        product.setDescription("2016년 트렌드인 넓은 소매의 깃 없는 일자형 자켓");
        realm.copyToRealm(product);

        id++;

        realm.commitTransaction();
    }

}
