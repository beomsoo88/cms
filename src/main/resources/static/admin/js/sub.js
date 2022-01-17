$(document).ready(function (){
    //file upload
    var fileTarget=$('.add-file input[type="file"]');
    fileTarget.on('change',function(){
        if(window.FileReader){
            var filename=$(this)[0].files[0].name;
        } else {
            var filename=$(this).val().split('/').pop().split('\\').pop();
        }

        $(this).siblings('.upload').val(filename);
    });

    //counsoler popup
    var regist = $('.open-pop'),
        counPopup = $('.counselor-popup'),
        popClose = $('.counselor-popup .close-pop'),
        dimm2 = $('.wrapper .dimm');

    regist.on('click',function () {
        counPopup.show();
        dimm2.addClass('active');
        $("body").css('overflow','visible').css('display','fixed');
    });

    var removeActive2 = function(){
        dimm2.removeClass('active');
        counPopup.hide();
        $("body").css('overflow','visible');
    };

    popClose.on('click',function () {
        removeActive2();
    });

    dimm2.on('click',function(){
        removeActive2();
    });

    //input numberOnly
    $("input:text[numberOnly]").on("keyup", function() {
        $(this).val($(this).val().replace(/[^0-9]/g,""));
    });

    //maxlength
    function maxLengthCheck(object){
        if (object.value.length > object.maxLength){
            object.value = object.value.slice(0, object.maxLength);
        }
    }

    //tooltip
    $.fn.animateAuto = function(prop, speed, callback) {
        var elem, width;
        return this.each(function(i, el) {
            el = $(el), elem = el.clone().css({
                "width": "auto"
            }).appendTo("body");
            width = elem.css("width"), elem.remove();

            if (prop === "width") {
                el.animate({
                    "width": width
                }, speed, callback);
            }
            else if (prop === "both") {
                el.animate({
                    "width": width
                }, speed, callback);
            }
        });
    };

    $('.tooltip').on('mouseenter',function() {
        $(this).find('p').stop().animateAuto('both', 800);
        $('.tbl-wrap').css('overflow-x','hidden');
    });
    $('.tooltip').on('mouseleave',function() {
        $(this).find('p').stop().animate({
            'width': 0
        },500);
    });

    //200722 input addComma
    function addCommas(x) {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }

    $(".addCom").on("keyup", function() {
        $(this).val(addCommas($(this).val().replace(/[^0-9]/g,"")));
    });

    //2020.10.13 add-cell
    var $changeEl = $('.tbl-wrap.change-tbl');

    $changeEl.each(function () {
        var $changeTbl = $(this).find('table'),
            $tbodyTr = $changeTbl.find('.address-input-wrap'),
            cellLength = $tbodyTr.length,
            $addTr = $(this).find('button.add-address'),
            addressBoxInput = $('.address-box input');

        if(cellLength < 2){
            $tbodyTr.find('.del-btn').hide();
        }

        $addTr.on('click',function () {

			if($("#zipCode").val() == null || $("#zipCode").val() == ""){
				alert("주소를 입력해주세요.");
				$("#zipCode").focus();
				return;
			}

			if($("#address").val() == null || $("#address").val() == ""){
				alert("주소를 입력해주세요.");
				$("#address").focus();
				return;
			}
			/*
			if($("#detailAddr").val() == null || $("#detailAddr").val() == ""){
				alert("상세주소를 입력해주세요.");
				$("#detailAddr").focus();
				return;
			}
			*/
            var trLength = $changeTbl.find('.address-input').length,
                codeVal = $('.address-box #zipCode').val(),
                adVal = $('.address-box #address').val(),
                detailVal = $('.address-box #detailAddr').val(),
                num = trLength;

            el = $('<div class="address-input">'+
                '<input type="text" id="ad'+num+'" name="address" value="('+codeVal+') '+adVal+'" disabled="disabled" class="int disabled ad">'+
                '<input type="text" id="detailAd'+num+'" name="detailAddr" value="'+detailVal+'" class="int mt5 detail-ad">'+
                '<button type="button" class="del-btn"></button>'+
                '</div>');

            $tbodyTr.append(el);
            changeId();
            addressBoxInput.val('');
        });

        changeId();
        function changeId(){
            var $fields = $changeTbl.find('.address-input');
            var count = 1;
            if($fields.length >= 2){
                $fields.addClass('del').find('.del-btn').show();
            } else {
                $fields.removeClass('del').find('.del-btn').hide();
            }

            $.each($fields, function () {
                $(this).find('.ad').attr('id', 'ad'+count);
                $(this).find('.detail-ad').attr('id', 'detailAd'+count);
                count++;
            });
        }

        $(document).on('click','.del-btn',function () {
            $(this).closest('.address-input').remove();

            changeId();
        });
    });

    //chart-tab
    var chartTab = $('.chart-top ul li');

    chartTab.click(function(e){
        e.preventDefault();
        $(this).addClass('active').siblings().removeClass('active');

        var tabIdx = $(this).index(),
            chartCon = $('.chart-wrap .chart');

        chartCon.eq(tabIdx).show().siblings().hide();

        if(chartCon.css('display')=='none'){
            $('.chart-right').hide();
        } else {
            $('.chart-right').show();
        }
    });

    //datepicker
    var $datepicker2 = $('.js-datepicker.maxnone');
    $datepicker2.datepicker({
        changeYear:true,
        changeMonth:true,
        setDate:'today',
        inline: true,
        showOtherMonths: true,
        dateFormat: 'yy-mm-dd',
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        monthNames : ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        showOn: "both",
        buttonImage: "/_admin/img/ico_calendar.png",
        buttonImageOnly: true,
        buttonText: "날짜 선택",
        minDate: null
    });

    var $datepicker = $('.js-datepicker');
    $datepicker.datepicker({
        changeYear:true,
        changeMonth:true,
        setDate:'today',
        inline: true,
        showOtherMonths: true,
        dateFormat: 'yy-mm-dd',
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        monthNames : ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        showOn: "both",
        buttonImage: "/_admin/img/ico_calendar.png",
        buttonImageOnly: true,
        buttonText: "날짜 선택",
        minDate: null
    });
    
    $('#startDate').datepicker("option", "maxDate", $('#endDate').val());
    $('#startDate').datepicker("option", "onClose", function (selectedDate){
        $('#endDate').datepicker( "option", "minDate", selectedDate );
    });

    $('#endDate').datepicker();
    $('#endDate').datepicker("option", "minDate", $('#startDate').val());
    $('#endDate').datepicker("option", "onClose", function (selectedDate){
        $('#startDate').datepicker( "option", "maxDate", selectedDate );
    });

    //dynamic add datepickerOptions
    $('body').on('focus','.add-cal',function () {
        $(this).datepicker(datepickerOptions);
    });

    var datepickerOptions = {
        changeYear: true,
        changeMonth: true,
        setDate: 'today',
        inline: true,
        showOtherMonths: true,
        dateFormat: 'yy-mm-dd',
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        showOn: "both",
        buttonImage: "/_admin/img/ico_calendar.png",
        buttonImageOnly: true,
        buttonText: "날짜 선택",
        minDate: null
    };
});