package com.example.genericfinder;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.geometry.LatLngBounds;
import com.naver.maps.map.CameraAnimation;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.util.FusedLocationSource;
import com.naver.maps.map.util.MarkerIcons;

import java.util.Vector;

public class PharmacyInfo extends Fragment implements OnMapReadyCallback {

    //지도 객체 변수
    //private MapView mapView;

    private static final String TAG = "PharmacyInfo";

    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    //네이버 지도 SDK에 현재 위치정보를 전달하는 객체
    private FusedLocationSource mLocationSource;
    private NaverMap mNaverMap;

    // 마커 정보 저장시킬 변수들 선언
    private Vector<LatLng> markersPosition; //위도 경도 정보
    private Vector<Marker> activeMarkers; //현재 마커들
    
    //마커 정보창
    private InfoWindow mInfoWindow;

    //기본생성자
    public PharmacyInfo(){

    }

    public static PharmacyInfo newInstance(){
        PharmacyInfo fragment = new PharmacyInfo();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_pharmacy_info,container,false);

        // 지도 객체 생성
        FragmentManager fm = getChildFragmentManager();
        MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.navermap);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.navermap, mapFragment).commit();
        }

        // getMapAsync를 호출하여 비동기로 onMapReady 콜백 메서드 호출
        // onMapReady에서 NaverMap 객체를 받음
        mapFragment.getMapAsync(this);

        // 위치를 반환하는 구현체인 FusedLocationSource 생성, 현재위치 담음
        mLocationSource =
                new FusedLocationSource(this, PERMISSION_REQUEST_CODE);

        return rootView;
    }

    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        Log.d( TAG, "onMapReady");

        //내부지도 표시 ON
        naverMap.setIndoorEnabled(true);

        //빌딩 표시
        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BUILDING, true);

        //카메라 대상을 한반도로 제한
        naverMap.setExtent(new LatLngBounds(new LatLng(34.826702, 126.698549), new LatLng(37.861234, 128.917787)));

        // naverMap 객체 받아서 mNaverMap 객체에 위치 소스 지정
        mNaverMap = naverMap;
        mNaverMap.setLocationSource(mLocationSource);

        //UI 세팅 - 나침반 끔 / 현재위치버튼 켬
        UiSettings uiSettings = mNaverMap.getUiSettings();
        uiSettings.setCompassEnabled(false);
        uiSettings.setLocationButtonEnabled(true);

        //현재위치로 지도 위치 설정
        naverMap.setLocationSource(mLocationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);

        //권한확인 방법 Activity랑 다름
        requestPermissions(PERMISSIONS, PERMISSION_REQUEST_CODE);

        // 카메라 초기 위치 설정
        LatLng initialPosition = new LatLng(36.145938, 128.392727); //학교 디지털관
        CameraUpdate cameraUpdate = CameraUpdate.scrollTo(initialPosition).animate(CameraAnimation.Easing);
        naverMap.moveCamera(cameraUpdate);

        //마커 위치 배열
        markersPosition = new Vector<LatLng>();

        //약국목록
        markersPosition.add(new LatLng(36.16105024213588, 128.34422689693605)); //호수약국
        markersPosition.add(new LatLng(36.152894476552234, 128.33295965433857)); //혜민약국
        markersPosition.add(new LatLng(36.152177539932666, 128.32994985873313)); //원호우리약국
        markersPosition.add(new LatLng(36.150197990344275, 128.33089071042895)); //세훈약국
        markersPosition.add(new LatLng(36.140530, 128.346208)); //한빛약국
        markersPosition.add(new LatLng(36.147177, 128.323693)); //상아약국
        markersPosition.add(new LatLng(36.13975737179969, 128.33245724870542)); //한사랑약국 , 연합약국
        markersPosition.add(new LatLng(36.14584854381878, 128.32276699693548)); //주민약국
        markersPosition.add(new LatLng(36.14353626590389, 128.32270844519707)); //감약국
        markersPosition.add(new LatLng(36.142444530055506, 128.3219906556424)); //국제약국
        markersPosition.add(new LatLng(36.139958775962064, 128.32331481424248)); //김온누리약국
        markersPosition.add(new LatLng(36.13973389875562, 128.32334419977303)); //하늘약국

        //약국목록 (1)
        markersPosition.add(new LatLng(36.13825564294535, 128.41981487346447)); //조은약국
        markersPosition.add(new LatLng(36.13807111212124, 128.42151016411475)); //옥계미소약국
        markersPosition.add(new LatLng(36.14692669221037, 128.41632805904925)); //인제약국
        markersPosition.add(new LatLng(36.13820172010287, 128.42024582768533)); //옥계21세기 약국
        markersPosition.add(new LatLng(36.138279, 128.416276)); //건강한약국
        markersPosition.add(new LatLng(36.13818343239395, 128.42075450540685)); //중앙약국
        markersPosition.add(new LatLng(36.13803112305341, 128.42180413459607)); //옥계하나약국
        markersPosition.add(new LatLng(36.138176, 128.421511)); //4공단약국
        markersPosition.add(new LatLng(36.13772968183433, 128.4220785586761)); //산호약국
        markersPosition.add(new LatLng(36.137662108596, 128.42212556051186)); //새롬약국
        markersPosition.add(new LatLng(36.13717289295971, 128.41654338162792)); //은혜약국
        markersPosition.add(new LatLng(36.137117, 128.416732)); //소망약국
        markersPosition.add(new LatLng(36.155387459897945, 128.42852466542317)); //산동제일약국
        markersPosition.add(new LatLng(36.15593596688027, 128.43115456016363)); //금빛희망약국
        markersPosition.add(new LatLng(36.15584333309943, 128.43173229979647)); //마음약국
        markersPosition.add(new LatLng(36.15591225461234, 128.43254801741656)); //민약국
        markersPosition.add(new LatLng(36.128871890496335, 128.4162862492484)); //수복약국

        //약국목록 (2)
        markersPosition.add(new LatLng(36.095179, 128.423484)); //사랑약국
        markersPosition.add(new LatLng(36.095398093806025, 128.42389934469813)); //문전약국
        markersPosition.add(new LatLng(36.095398093806025, 128.42389934469813)); //새보령약국
        markersPosition.add(new LatLng(36.098058729192694, 128.42772714401147)); //한우리약국
        markersPosition.add(new LatLng(36.104118375369154, 128.4254918859265)); //광제약국
        markersPosition.add(new LatLng(36.09558688376711, 128.429404601751)); //다정약국
        markersPosition.add(new LatLng(36.10595298157033, 128.42021698560362)); //수연약국
        markersPosition.add(new LatLng(36.096035936846825, 128.43028260566155)); //정우약국
        markersPosition.add(new LatLng(36.10625965012055, 128.41993150727936)); //명인당약국
        markersPosition.add(new LatLng(36.10677069764502, 128.41950797298827)); //장생약국
        markersPosition.add(new LatLng(36.10632740140508, 128.42482505445648)); //우리약국
        markersPosition.add(new LatLng(36.093941744358425, 128.4328705433329)); //구평우리약국
        markersPosition.add(new LatLng(36.1073673573218, 128.42165688725234)); //안지약국
        markersPosition.add(new LatLng(36.10726933941692, 128.41890154800674)); //우정약국
        markersPosition.add(new LatLng(36.107665670131574, 128.41868542497)); //승현약국
        markersPosition.add(new LatLng(36.10828096646997, 128.422891747023)); //메디팜현도약국
        markersPosition.add(new LatLng(36.09404484211628, 128.43237294983743)); //행복한약국
        markersPosition.add(new LatLng(36.108701496801366, 128.42302948236355)); //온누리성모약국
        markersPosition.add(new LatLng(36.10859522470732, 128.42243906480363)); //명일약국
        markersPosition.add(new LatLng(36.09313367578232, 128.43250466903777)); //구평경북약국
        markersPosition.add(new LatLng(36.09269842353185, 128.4322770760665)); //새상아약국
        markersPosition.add(new LatLng(36.094666398261374, 128.43354442304485)); //경희편한약국
        markersPosition.add(new LatLng(36.09269229320124, 128.43273226200898)); //솔약국
        markersPosition.add(new LatLng(36.10632795396631, 128.40417783898383)); //동구미이마트약국
        markersPosition.add(new LatLng(36.107247852010346, 128.40244371777146)); //구미정문약국
        markersPosition.add(new LatLng(36.116939360650655, 128.42533660977912)); //종근당약국
        markersPosition.add(new LatLng(36.07998641468638, 128.41279148006976)); //석적경북약국
        markersPosition.add(new LatLng(36.0798004127061, 128.4118187948726)); //사랑약국
        markersPosition.add(new LatLng(36.08037454389953, 128.41248226972306)); //소망약국
        markersPosition.add(new LatLng(36.08049160171278, 128.41262308568972)); //늘푸른약국
        markersPosition.add(new LatLng(36.078011050027015, 128.40973315432126)); //새봄약국
        markersPosition.add(new LatLng(36.07934267337265, 128.41131793550466)); //굿데이약국
        markersPosition.add(new LatLng(36.11885672212736, 128.42625243688178)); //근화당약국

        //약국목록 (3)
        markersPosition.add(new LatLng(36.10946424270407, 128.3649184252444)); //구미이마트약국
        markersPosition.add(new LatLng(36.11375672836289, 128.36537108342765)); //마트약국
        markersPosition.add(new LatLng(36.102744521223656, 128.36374958771165)); //프라자약국
        markersPosition.add(new LatLng(36.114585509784625, 128.38077467181546)); //구미대형약국
        markersPosition.add(new LatLng(36.1016219520572, 128.3829181293841)); //중원약국
        markersPosition.add(new LatLng(36.101713126223416, 128.38304138373525)); //금오약국
        markersPosition.add(new LatLng(36.10162357676664, 128.38291746026817)); //대학약국
        markersPosition.add(new LatLng(36.1023219533422, 128.38462567519423)); //벧엘약국
        markersPosition.add(new LatLng(36.10272369140608, 128.383979730576)); //백초약국
        markersPosition.add(new LatLng(36.12138147292643, 128.37278970797306)); //오즈약국
        markersPosition.add(new LatLng(36.12080366224203, 128.3742361431869)); //강변약국
        markersPosition.add(new LatLng(36.12203816305549, 128.36315270934315)); //평화약국
        markersPosition.add(new LatLng(36.09562158291528, 128.35848849647138)); //해맑은약국
        markersPosition.add(new LatLng(36.123263287600764, 128.3647872874496)); //정약국
        markersPosition.add(new LatLng(36.122900144268854, 128.36351783784562)); //아시아약국
        markersPosition.add(new LatLng(36.09678244664902, 128.35640631314791)); //유하약국
        markersPosition.add(new LatLng(36.121708823786996, 128.36435286256378)); //효성약국
        markersPosition.add(new LatLng(36.12346320666539, 128.36202068492742)); //신평약국
        markersPosition.add(new LatLng(36.123912916221336, 128.36237316624334)); //일신약국
        markersPosition.add(new LatLng(36.119228824507985, 128.34895952981253)); //송정약국
        markersPosition.add(new LatLng(36.11945779911521, 128.34867375336015)); //예명약국
        markersPosition.add(new LatLng(36.090045319996875, 128.3559316116011)); //호산나약국
        markersPosition.add(new LatLng(36.122295917897866, 128.3503329368689)); //육일약국
        markersPosition.add(new LatLng(36.12270517299058, 128.3506557348484)); //터미널약국
        markersPosition.add(new LatLng(36.0850489502281, 128.3629603335087)); //임은하나약국
        markersPosition.add(new LatLng(36.08898255823914, 128.3549337611229)); //햇살약국
        markersPosition.add(new LatLng(36.088163053960585, 128.3555566114375)); //상모약국
        markersPosition.add(new LatLng(36.08742003188638, 128.35564819790594)); //민해약국
        markersPosition.add(new LatLng(36.08679827284356, 128.35519670334094)); //대한약국
        markersPosition.add(new LatLng(36.08632249158634, 128.3557398506639)); //상모제일약국

        //약국목록 (4)
        markersPosition.add(new LatLng(36.131512894156806, 128.32753231956465)); //한솔
        markersPosition.add(new LatLng(36.12962164962905, 128.32924267120697)); //부활
        markersPosition.add(new LatLng(36.13005713717238, 128.32754872544203)); //늘사랑
        markersPosition.add(new LatLng(36.12978516835331, 128.32962739673286)); //유명
        markersPosition.add(new LatLng(36.13003669026795, 128.3290512726012)); //신통한
        markersPosition.add(new LatLng(36.131060435035415, 128.32700560045444)); //소현
        markersPosition.add(new LatLng(36.128825507755025, 128.3319521059323)); //은행
        markersPosition.add(new LatLng(36.13330242951278, 128.3307455867167)); //신현대
        markersPosition.add(new LatLng(36.12821660637195, 128.32841277770336)); //청솔
        markersPosition.add(new LatLng(36.12984358249025, 128.3318238946351)); //카톨릭종합
        markersPosition.add(new LatLng(36.12786074739707, 128.33468318692792)); //수정
        markersPosition.add(new LatLng(36.12759280832702, 128.3348809484541)); //싱싱
        markersPosition.add(new LatLng(36.12750907718157, 128.33501491593364)); //새한솔
        markersPosition.add(new LatLng(36.13076362165881, 128.3224469006819)); //원남
        markersPosition.add(new LatLng(36.12935229243852, 128.32996235929136)); //안심
        markersPosition.add(new LatLng(36.131079114891975, 128.33256524696708)); //신세계
        markersPosition.add(new LatLng(36.12920196872901, 128.31924391617014)); //큰사랑
        markersPosition.add(new LatLng(36.13133123224348, 128.32808189906473)); //토마토
        markersPosition.add(new LatLng(36.12942582783501, 128.33340244678607)); //길
        markersPosition.add(new LatLng(36.13040100039238, 128.3229043486252)); //원남
        markersPosition.add(new LatLng(36.15183264737131, 128.3211436723384)); //한누리
        markersPosition.add(new LatLng(36.12304354207384, 128.33979238970002)); //나눔
        markersPosition.add(new LatLng(36.12155439478358, 128.3406406443169)); //음양
        markersPosition.add(new LatLng(36.11495327557772, 128.3320834875781)); //시티
        markersPosition.add(new LatLng(36.12470462664682, 128.34265658320413)); //동성
        markersPosition.add(new LatLng(36.116865524781915, 128.33968550771468)); //21세기
        markersPosition.add(new LatLng(36.114038101597586, 128.33984898294034)); //메디팜조아
        markersPosition.add(new LatLng(36.11487147566981, 128.33871065317612)); //로뎀
        markersPosition.add(new LatLng(36.13973381268065, 128.323342936253)); //하늘
        markersPosition.add(new LatLng(36.11533233523485, 128.33961331399036)); //이레
        markersPosition.add(new LatLng(36.13976259257458, 128.3324972905589)); //한사랑
        markersPosition.add(new LatLng(36.13995943571055, 128.3233127847984)); //김온누리
        markersPosition.add(new LatLng(36.114278607215276, 128.33869705528156)); //다인
        markersPosition.add(new LatLng(36.11407056429417, 128.33865398271433)); //바른
        markersPosition.add(new LatLng(36.11503779190887, 128.34031949288328)); //수
        markersPosition.add(new LatLng(36.114924825351494, 128.34009394689937)); //예일
        markersPosition.add(new LatLng(36.11446329993273, 128.33984132762524)); //이롬
        markersPosition.add(new LatLng(36.11448427683982, 128.3398499981283)); //혜성
        markersPosition.add(new LatLng(36.12875576208536, 128.33402913930078)); //우리들
        markersPosition.add(new LatLng(36.11403888371577, 128.33989559890904)); //처음온누리
        markersPosition.add(new LatLng(36.13912039802209, 128.31753486161045)); //세종온누리
        markersPosition.add(new LatLng(36.14244433192893, 128.3219909137437)); //국제
        markersPosition.add(new LatLng(36.11431664780516, 128.3398499981283)); //형곡비타민
        markersPosition.add(new LatLng(36.138516531774975, 128.31811042192913)); //봄
        markersPosition.add(new LatLng(36.137813499441556, 128.32094735669136)); //코끼리
        markersPosition.add(new LatLng(36.1505946359702, 128.3053432685296)); //동산
        markersPosition.add(new LatLng(36.11116781926229, 128.33631105784133)); //새경북
        markersPosition.add(new LatLng(36.139171363148506, 128.3157111552579)); //예진
        markersPosition.add(new LatLng(36.112707218371504, 128.33963084186638)); //행복
        markersPosition.add(new LatLng(36.110625275039354, 128.33551133350812)); //메디팜작은별
        markersPosition.add(new LatLng(36.11048758026855, 128.33571586072952)); //미르
        markersPosition.add(new LatLng(36.11222139585827, 128.3398225558249)); //대일
        markersPosition.add(new LatLng(36.14982208266781, 128.309360838986)); //구미365
        markersPosition.add(new LatLng(36.11945632055289, 128.34867266813472)); //예명
        markersPosition.add(new LatLng(36.14353627727506, 128.3227157893699)); //감
        markersPosition.add(new LatLng(36.142466159118776, 128.31646693407203)); //하나온누리
        markersPosition.add(new LatLng(36.11923250925047, 128.34896110081561)); //송정
        markersPosition.add(new LatLng(36.142640886264, 128.31507688310705)); //열린
        markersPosition.add(new LatLng(36.10710046214277, 128.335795397585)); //참조은
        markersPosition.add(new LatLng(36.106845350352664, 128.33579652531517)); //서울
        markersPosition.add(new LatLng(36.14584077321496, 128.32276435777374)); //주민
        markersPosition.add(new LatLng(36.10692633272243, 128.3376259034436)); //은
        markersPosition.add(new LatLng(36.14053722962755, 128.34621050447097)); //한빛
        markersPosition.add(new LatLng(36.14717213058928, 128.32368378532183)); //상아

        //인포윈도우 어댑터
        mInfoWindow = new InfoWindow();
        mInfoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(this.getContext()) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "  정상 영업 중 \n 매주 일요일 휴무";
            }
        });

        //마커 클릭 리스너
        Overlay.OnClickListener listener = overlay -> {
            Marker marker = (Marker)overlay;

            if (marker.getInfoWindow() == null){
                mInfoWindow.open(marker);
            }
            else {
                mInfoWindow.close();
                overlay.setMap(null); //마커 삭제
            }
            return true;
        };


        // 카메라 이동 이벤트
        naverMap.addOnCameraChangeListener(new NaverMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(int reason, boolean animated) {
                freeActiveMarkers();
                // 정의된 마커위치들중 가시거리 내에 있는 것들만 마커 생성
                LatLng currentPosition = getCurrentPosition(naverMap);
                for (LatLng markerPosition: markersPosition) {
                    if (!withinSightMarker(currentPosition, markerPosition))
                        continue;
                    Marker marker = new Marker();
                    marker.setPosition(markerPosition);
                    marker.setWidth(Marker.SIZE_AUTO);
                    marker.setHeight(Marker.SIZE_AUTO);
                    marker.setIcon(MarkerIcons.BLACK);
                    marker.setIconTintColor(Color.rgb(16,5,114));
                    marker.setCaptionText("약국");
                    marker.setCaptionColor(Color.rgb(16,5,114));
                    marker.setCaptionHaloColor(Color.rgb(223,220,254));
                    marker.setMap(naverMap);
                    marker.setOnClickListener(listener); //onClick 추가
                    activeMarkers.add(marker);
                }
            }
        });

        //지도 클릭 이벤트
        naverMap.setOnMapClickListener((point,coord)->{
            mInfoWindow.close(); //인포윈도우 지우기
        });

        //지도 롱 클릭
        naverMap.setOnMapLongClickListener((point,coord)->{
            //클릭한 좌표 출력해줌
            Toast.makeText(this.getContext(),getString(R.string.format_map_long_click,coord.latitude,coord.longitude),Toast.LENGTH_SHORT).show();
        });

    } //End of OnMapReady


    //권한 획득 확인
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mNaverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
            }
        }
    }

    // 현재 카메라가 보고있는 위치
    public LatLng getCurrentPosition(NaverMap naverMap) {
        CameraPosition cameraPosition = naverMap.getCameraPosition();
        return new LatLng(cameraPosition.target.latitude, cameraPosition.target.longitude);
    }

    // 선택한 마커의 위치가 가시거리(카메라가 보고있는 위치 반경 2km 내)에 있는지 확인
    public final static double REFERANCE_LAT = 1 / 109.958489129649955;
    public final static double REFERANCE_LNG = 1 / 88.74;
    public final static double REFERANCE_LAT_X2 = 2 / 109.958489129649955;
    public final static double REFERANCE_LNG_X2 = 2 / 88.74;
    public boolean withinSightMarker(LatLng currentPosition, LatLng markerPosition) {
        boolean withinSightMarkerLat = Math.abs(currentPosition.latitude - markerPosition.latitude) <= REFERANCE_LAT_X2;
        boolean withinSightMarkerLng = Math.abs(currentPosition.longitude - markerPosition.longitude) <= REFERANCE_LNG_X2;
        return withinSightMarkerLat && withinSightMarkerLng;
    }

    // 가시거리 밖에 마커들 지도에서 삭제
    private void freeActiveMarkers() {
        if (activeMarkers == null) {
            activeMarkers = new Vector<Marker>();
            return;
        }
        for (Marker activeMarker: activeMarkers) {
            activeMarker.setMap(null);
        }
        activeMarkers = new Vector<Marker>();
    }

}
