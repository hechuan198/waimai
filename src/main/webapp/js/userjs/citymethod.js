

/*根据id获取对象*/
function $(str) {
    return document.getElementById(str);
}

var prov = $('receiverProvince');
var city = $('receiverCity');
var country = $('receiverDistrict');

/*用于保存当前所选的省市区*/
var current = {
    prov: '',
    city: '',
    country: ''
};

/*自动加载省份列表*/
(function showProv() {
    var len = provice.length;
    for (var i = 0; i < len; i++) {
        var provOpt = document.createElement('option');
        provOpt.innerText = provice[i]['name'];
        provOpt.value = provice[i]['name'];
        provOpt.setAttribute('attr_name',i);
        prov.appendChild(provOpt);
    }
})();

/*根据所选的省份来显示城市列表*/
function showCity(obj) {
    city.options.length = 0;
    var cityselect = document.createElement('option');
    cityselect.innerText = "=请选择城市=";
    cityselect.value = "";
    city.appendChild(cityselect);

    var val = obj.options[obj.selectedIndex].getAttribute("attr_name");
    if (val != current.prov) {
        current.prov = val;
    }
    console.log(val);
    // console.log(provice[val]["city"]);
    if (val != null) {
        city.length = 1;
        var cityLen = provice[val]['city'].length;
        for (var j = 0; j < cityLen; j++) {
            var cityOpt = document.createElement('option');
            cityOpt.innerText = provice[val]['city'][j].name;
            cityOpt.value = provice[val]['city'][j].name;
            cityOpt.setAttribute('attr_name',j);
            city.appendChild(cityOpt);
        }
    }
}

/*根据所选的城市来显示县区列表*/
function showCountry(obj) {
    country.options.length = 0;
    var countryselect = document.createElement('option');
    countryselect.innerText = "=请选择县区=";
    countryselect.value = "";
    country.appendChild(countryselect);

    var val = obj.options[obj.selectedIndex].getAttribute("attr_name");
    current.city = val;
    if (val != null) {
        country.length = 1; //清空之前的内容只留第一个默认选项
        var countryLen = provice[current.prov]["city"][val].districtAndCounty.length;
        for (var n = 0; n < countryLen; n++) {
            var countryOpt = document.createElement('option');
            countryOpt.innerText = provice[current.prov]['city'][val].districtAndCounty[n];
            countryOpt.value = provice[current.prov]['city'][val].districtAndCounty[n];
            country.appendChild(countryOpt);
        }
    }
}



