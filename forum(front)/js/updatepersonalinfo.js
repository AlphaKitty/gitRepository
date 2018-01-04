/**
 * Created by Administrator on 2017-3-31.
 */

window['year'] = "";
window['month'] = "";
window['day'] = "";
window['birth'] = "";

window['bloodtype'] = "";

window['province_birth'] = "";
window['city_birth'] = "";
window['district_birth'] = "";
window['birthplace'] = "";

window['province_live'] = "";
window['city_live'] = "";
window['district_live'] = "";
window['liveplace'] = "";

window['introduce'] = "";

$(document).ready( function() {

    console.log("这里是updatepersonalinfo.js");
    // console.log($('.line30')[0].textContent);
    year=$('.cus-sel-opt-panel')[0].textContent;
    month=$('.cus-sel-opt-panel')[1].textContent;
    day=$('.cus-sel-opt-panel')[2].textContent;
    bloodtype=$('.cus-sel-opt-panel')[3].textContent;
    province_birth=$('.cus-sel-opt-panel')[4].textContent;
    city_birth=$('.cus-sel-opt-panel')[6].textContent;
    district_birth=$('.cus-sel-opt-panel')[7].textContent;
    province_live=$('.cus-sel-opt-panel')[8].textContent;
    city_live=$('.cus-sel-opt-panel')[10].textContent;
    district_live=$('.cus-sel-opt-panel')[11].textContent;
    introduce=$('#passport_userdetail')[0].textContent;

    birth = year+"年"+month+"月"+day+"日";
    console.log(birth);
    console.log(bloodtype);
    birthplace = province_birth+city_birth+district_birth;
    console.log(birthplace);
    liveplace = province_live+city_live+district_live;
    console.log(introduce);
    /*test*/
    $('#test').click(function () {
        console.log("事件触发");
        console.log($('.container').html());
        $('.jumbotron').remove();
    });

    $('.active').click(function () {
        console.log("事件触发");
        console.log($('.container').html());
    });

});