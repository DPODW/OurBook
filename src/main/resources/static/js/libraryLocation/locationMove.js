function searchAddressToCoordinate(address) {
    naver.maps.Service.geocode({
        query: address
    }, function(status, response) {
        if (status === naver.maps.Service.Status.ERROR) {
            return console.log("검색 에러")
        }
        if (response.v2.meta.totalCount === 0) {
            return alert('주소 검색 결과가 없습니다. (네이버 지도 검색 결과 0)');
        }
        item = response.v2.addresses[0];
        insertAddress(item.roadAddress, item.x, item.y);

    });
}
$(document).on('click','.locationCheck', function(e) {
    let index = $(this).data('index');
    let libraryTitles = document.querySelectorAll('.libraryTitle');

    // 선택된 옵션의 값 가져오기
    let selectedValue = libraryTitles[index].textContent.replace("도서관 주소: ","");
    console.log(selectedValue);
    e.preventDefault();
    searchAddressToCoordinate(selectedValue);
});
naver.maps.Event.once(map, 'init_stylemap', initGeocoder);



//검색정보를 테이블로 작성해주고, 지도에 마커를 찍어준다.
function insertAddress(address, latitude, longitude) {
    let map = new naver.maps.Map('map', {
        center: new naver.maps.LatLng(longitude, latitude),
        zoom: 14
    });
    let marker = new naver.maps.Marker({
        map: map,
        position: new naver.maps.LatLng(longitude, latitude),
    });
    window.scrollTo({ top: 0, behavior: 'smooth' });
}