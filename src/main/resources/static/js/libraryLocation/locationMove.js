function searchAddressToCoordinate(address) {
    naver.maps.Service.geocode({
        query: address
    }, function(status, response) {
        if (status === naver.maps.Service.Status.ERROR) {
            return alert('Something Wrong!');
        }
        if (response.v2.meta.totalCount === 0) {
            return alert('올바른 주소를 입력해주세요.');
        }
        var htmlAddresses = [],
            item = response.v2.addresses[0],
            point = new naver.maps.Point(item.x, item.y);
        if (item.roadAddress) {
            htmlAddresses.push('[도로명 주소] ' + item.roadAddress);
        }
        if (item.jibunAddress) {
            htmlAddresses.push('[지번 주소] ' + item.jibunAddress);
        }
        if (item.englishAddress) {
            htmlAddresses.push('[영문명 주소] ' + item.englishAddress);
        }

        insertAddress(item.roadAddress, item.x, item.y);

    });
}

// 주소 검색의 이벤트
$('#FirstDistrict').on('keydown', function(e) {
    var selectFirstElement = document.getElementById("FirstDistrict");
    var selectSecondElement = document.getElementById("SecondDistrict");

    // 선택된 옵션의 값 가져오기
    var selectedValue = selectFirstElement.value + selectSecondElement.value;
    console.log(selectedValue);

    var keyCode = e.which;
    if (keyCode === 13) { // Enter Key
        searchAddressToCoordinate(selectedValue);
    }
});
$('#locationCheck').on('click', function(e) {
    var selectFirstElement = document.getElementById("FirstDistrict");
    var selectSecondElement = document.getElementById("SecondDistrict");

    // 선택된 옵션의 값 가져오기
    var selectedValue = selectFirstElement.value + selectSecondElement.value;
    e.preventDefault();
    searchAddressToCoordinate(selectedValue);
});
naver.maps.Event.once(map, 'init_stylemap', initGeocoder);



//검색정보를 테이블로 작성해주고, 지도에 마커를 찍어준다.
function insertAddress(address, latitude, longitude) {
    var map = new naver.maps.Map('map', {
        center: new naver.maps.LatLng(longitude, latitude),
        zoom: 14
    });
    var marker = new naver.maps.Marker({
        map: map,
        position: new naver.maps.LatLng(longitude, latitude),
    });
}