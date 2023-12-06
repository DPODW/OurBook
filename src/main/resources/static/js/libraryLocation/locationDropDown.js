window.onload = function () {
    // 페이지 로드 시 저장된 값을 읽어와서 선택 상태를 복원
    let firstDistrict = document.getElementById("FirstDistrict");
    let secondDistrict = document.getElementById("SecondDistrict");

    let storedFirstDistrictValue = localStorage.getItem("firstDistrict");
    let storedSecondDistrictValue = localStorage.getItem("secondDistrict");

    if (storedFirstDistrictValue) {
        firstDistrict.value = storedFirstDistrictValue;
        updateLocation(); // 두 번째 드롭다운도 업데이트
        if (storedSecondDistrictValue) {
            secondDistrict.value = storedSecondDistrictValue;
        }
    }

    // 첫 번째 드롭다운 변경 시 로컬 스토리지에 저장
    firstDistrict.addEventListener("change", function () {
        localStorage.setItem("firstDistrict", firstDistrict.value);
        updateLocation();

        //두번째 드롭아웃을 미리보기로 전송할 시 -> 클릭되지 않은것으로 간주되고(변경이 일어나지 않은것으로 간주) 새로고침시 공백이 유지됌. 고로
        //첫번째 드롭아웃이 변경 -> 두번째 드롭아웃도 같이 저장해주어야 함. (상태 저장)
        localStorage.setItem("secondDistrict", secondDistrict.value);
    });

    // 두 번째 드롭다운 변경 시 로컬 스토리지에 저장
    secondDistrict.addEventListener("change", function () {
        localStorage.setItem("secondDistrict", secondDistrict.value);
    });
};
function updateLocation() {
    // 첫 번째 드롭다운의 선택된 값을 가져옴
    let FirstDistrict = document.getElementById("FirstDistrict");
    let selectedValue = FirstDistrict.options[FirstDistrict.selectedIndex].value;

    localStorage.setItem("selectedFirstDistrict", selectedValue);
    // 두 번째 드롭다운의 옵션을 초기화
    let SecondDistrict = document.getElementById("SecondDistrict");
    SecondDistrict.innerHTML = ""; // 기존 옵션 제거
    // 선택된 값에 따라 두 번째 드롭다운에 옵션을 추가
    switch (selectedValue) {
        case "서울특별시":
            addOptions(SecondDistrict, ["종로구", "중구", "용산구", "성동구", "광진구", "동대문구", "중랑구", "성북구", "강북구", "도봉구", "노원구", "은평구", "서대문구", "마포구", "양천구", "강서구", "구로구", "금천구", "영등포구", "동작구", "관악구", "서초구", "강남구", "송파구","강동구"]);
            break;
        case "부산광역시":
            addOptions(SecondDistrict,  ["중구", "서구", "동구", "영도구", "부산진구", "동래구", "남구", "북구", "해운대구", "사하구", "금정구", "강서구", "연제구", "수영구", "사상구", "기장군"]);
            break;
        case "대구광역시":
            addOptions(SecondDistrict,   ["중구", "동구", "서구", "남구", "북구", "수성구", "달서구", "달성군"]);
            break;
        case "인천광역시":
            addOptions(SecondDistrict,  ["중구", "동구", "남구", "미추홀구", "연수구", "남동구", "부평구", "계양구", "서구", "강화군", "옹진군"]);
            break;
        case "광주광역시":
            addOptions(SecondDistrict,  ["동구", "서구", "남구", "북구", "광산구"]);
            break;
        case "대전광역시":
            addOptions(SecondDistrict,  ["동구", "중구", "서구", "유성구", "대덕구"]);
            break;
        case "울산광역시":
            addOptions(SecondDistrict,  ["중구", "남구", "동구", "북구", "울주군"]);
            break;
        case "세종특별자치시":
            addOptions(SecondDistrict, [""]);
            break;
        case "경기도":
            addOptions(SecondDistrict,  ["수원시", "성남시", "고양시", "용인시", "부천시", "안산시", "안양시", "남양주시", "화성시", "평택시", "의정부시", "시흥시", "파주시", "광명시", "김포시", "군포시", "광주시", "이천시", "양주시", "오산시", "구리시", "안성시", "포천시", "의왕시", "하남시", "여주시", "여주군", "양평군", "동두천시", "과천시", "가평군", "연천군"]);
            break;
        case "강원도":
            addOptions(SecondDistrict,   ["춘천시", "원주시", "강릉시", "동해시", "태백시", "속초시", "삼척시", "홍천군", "횡성군", "영월군", "평창군", "정선군", "철원군", "화천군", "양구군", "인제군", "고성군", "양양군"]);
            break;
        case "충청북도":
            addOptions(SecondDistrict,  ["청주시", "충주시", "제천시", "청원군", "보은군", "옥천군", "영동군", "진천군", "괴산군", "음성군", "단양군", "증평군"]);
            break;
        case "충청남도":
            addOptions(SecondDistrict,  ["천안시", "공주시", "보령시", "아산시", "서산시", "논산시", "계룡시", "당진시", "당진군", "금산군", "연기군", "부여군", "서천군", "청양군", "홍성군", "예산군", "태안군"]);
            break;
        case "전라북도":
            addOptions(SecondDistrict,  ["전주시", "군산시", "익산시", "정읍시", "남원시", "김제시", "완주군", "진안군", "무주군", "장수군", "임실군", "순창군", "고창군", "부안군"]);
            break;
        case "전라남도":
            addOptions(SecondDistrict,  ["목포시", "여수시", "순천시", "나주시", "광양시", "담양군", "곡성군", "구례군", "고흥군", "보성군", "화순군", "장흥군", "강진군", "해남군", "영암군", "무안군", "함평군", "영광군", "장성군", "완도군", "진도군", "신안군"]);
            break;
        case "경상북도":
            addOptions(SecondDistrict,  ["포항시", "경주시", "김천시", "안동시", "구미시", "영주시", "영천시", "상주시", "문경시", "경산시", "군위군", "의성군", "청송군", "영양군", "영덕군", "청도군", "고령군", "성주군", "칠곡군", "예천군", "봉화군", "울진군", "울릉군"]);
            break;
        case "경상남도":
            addOptions(SecondDistrict,  ["창원시", "마산시", "진주시", "진해시", "통영시", "사천시", "김해시", "밀양시", "거제시", "양산시", "의령군", "함안군", "창녕군", "고성군", "남해군", "하동군", "산청군", "함양군", "거창군", "합천군"]);
            break;
        case "제주특별자치도":
            addOptions(SecondDistrict,  ["제주시", "서귀포시"]);
            break;

    }
}

function addOptions(select, options) {
    for (let i = 0; i < options.length; i++) {
        let option = document.createElement("option");
        option.value = options[i];
        option.text = options[i];
        select.add(option);
    }
}