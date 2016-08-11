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
        color.setLab("45.3/21.3/12.8");
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

        resource.setId(id);
        resource.setName("쪽풀");
        resource.setScientificName("Indigo");
        resource.setDescription("한해살이풀, 잎을 파란색 염료로 사용하며 풀기와 씨를 해독제, 해열제 등으로 이용함.");
        realm.copyToRealm(resource);

        color.setId(id);
        color.setName("Blue Indigo");
        color.setLab("43.14/-10.94/-16.04");
        color.setHvc("6.29B 418/449");
        color.setRgb("72 84 112");
        color.setHexhtml("485484");
        realm.copyToRealm(color);

        fabric.setId(id);
        fabric.setName("순면 poplin 원단");
        fabric.setDescription("cotton 100%, poplin");
        realm.copyToRealm(fabric);

        product.setId(id);
        product.setName("버튼 칼라의 셔츠");
        product.setDescription("기본 스타일의 셔츠");
        realm.copyToRealm(product);

        id++;

        resource.setId(id);
        resource.setName("울금");
        resource.setScientificName("Tumeric");
        resource.setDescription("맵고 쓴맛을 내는 향신료. 주 성분인 '커큐민'은 울금의 노란 빛깔을 만드는 성분으로 피부 염증을 가라앉혀 피부 미용에도 큰 도움이 됨.");
        realm.copyToRealm(resource);

        color.setId(id);
        color.setName("Blazing Yellow");
        color.setLab("75.53/8.80/88.10");
        color.setHvc("0.82Y 7.40/13.16");
        color.setRgb("254 231 21");
        color.setHexhtml("948260");
        realm.copyToRealm(color);

        fabric.setId(id);
        fabric.setName("섬유 종류별 염색 원단");
        fabric.setDescription("acetate, cotton, nylon, Polyester, acrylic, wool");
        realm.copyToRealm(fabric);

        product.setId(id);
        product.setName("빈티지 스타일의 쿠션");
        product.setDescription("홀치기염으로 자연스러운 무늬를 표현한 인테리어용 쿠션");
        realm.copyToRealm(product);

        id++;

        resource.setId(id);
        resource.setName("감귤");
        resource.setScientificName("Citrus Unshiu");
        resource.setDescription("한국·일본·중남미·흑해 등지에 분포하는 상록성 소교목의 과실. 한국의 제주도가 재배가능한 최북단의 지역으로 항산화성과 항균성, 보습성이 우수함.");
        realm.copyToRealm(resource);

        color.setId(id);
        color.setName("Lemonade");
        color.setLab("78.53/-1.42/34.86");
        color.setHvc("3.78Y 7.71/4.9 2/3.4");
        color.setRgb("239 229 154");
        color.setHexhtml("EFE59A");
        realm.copyToRealm(color);

        fabric.setId(id);
        fabric.setName("순면 새틴 원단");
        fabric.setDescription("cotton 100%, sateen");
        realm.copyToRealm(fabric);

        product.setId(id);
        product.setName("동남아풍의 와이드 쇼울");
        product.setDescription("농도를 달리하여 감귤로 염색한 와이드 쇼울");
        realm.copyToRealm(product);

        id++;

        resource.setId(id);
        resource.setName("연지충");
        resource.setScientificName("cochineal");
        resource.setDescription("선인장의 표면에 기생하는 패각충의 일종인 코치닐 곤충. 수컷의 몸에서 선명한 홍색의 염료가 만들어짐. 멕시코나 페루 등지에서 생산됨.");
        realm.copyToRealm(resource);

        color.setId(id);
        color.setName("Beetroot Purple");
        color.setLab("37.97/36.84/3.24");
        color.setHvc("8.94RP 3.69/8.03");
        color.setRgb("203 47 112");
        color.setHexhtml("CB2F70");
        realm.copyToRealm(color);

        fabric.setId(id);
        fabric.setName("섬유 종류별 염색 원단");
        fabric.setDescription("acetate, cotton, nylon, Polyester, acrylic, wool");
        realm.copyToRealm(fabric);

        product.setId(id);
        product.setName("린넨 스카프");
        product.setDescription("농도를 달리해서 침염한 하절기용 린넨 스카프");
        realm.copyToRealm(product);

        id++;

        resource.setId(id);
        resource.setName("풋감");
        resource.setScientificName("Persimmon");
        resource.setDescription("7~8월에 채집하는 익지않은 풋감. 즙을 으깨어 염색하여 햇빛에 노출시키면 갈색으로 짙고 어두워짐. 향균성과 자외선차단성, 투습성이 향상됨.");
        realm.copyToRealm(resource);

        color.setId(id);
        color.setName("Autumn Sunset");
        color.setLab("67.19/21.08/37.68");
        color.setHvc("4.25YR 6.55/7.67");
        color.setRgb("244 140 87");
        color.setHexhtml("F58C57");
        realm.copyToRealm(color);

        fabric.setId(id);
        fabric.setName("순면 새틴 원단");
        fabric.setDescription("cotton 100%, sateen");
        realm.copyToRealm(fabric);

        product.setId(id);
        product.setName("나뭇잎 아플리케 블라우스");
        product.setDescription("2016년 트렌드인 넓은 소매의 깃 없는 일자형 자켓");
        realm.copyToRealm(product);

        id++;

        realm.commitTransaction();
    }

}
