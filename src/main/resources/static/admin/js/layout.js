$(document).ready(function () {
    //메뉴 dep열고 닫기
    $('.dep-wrap').each(function () {
        var $childBtn = $(this).find('.child-btn');

        $childBtn.on('click',function () {
            var $depWrap = $('.dep-wrap');
            var $listDep = $(this).attr('class');
            var $depSplit = $listDep.split(' ').reverse()[0];
            var num = 10;
            // console.log($depSplit);

            if(!$(this).parents('tr').hasClass('on')){
                $(this).parents('tr').addClass('on').removeClass('off');
                for(i=0; i < num; i++){
                    var classNo = $depSplit +'-'+ i;
                    $depWrap.find('.'+classNo).parents('tr').addClass('show').removeClass('off').find('td').css('border-bottom','1px solid #eee').find('div').stop().animate({
                        'min-height': '20px',
                        'padding': '14px 10px',
                    },300);
                    // console.log(classNo);
                }
            } else {
                var selClass = $(this).parents('tr').attr('class');
                var thisNo = selClass.replace(/[^0-9]/g,'');
                var nextSelector = $(this).parents('tr').next();
                var nextNo = nextSelector.attr("class").replace(/[^0-9]/g,'');

                while (true){
                    if(thisNo < nextNo){
                        //console.log(nextSelector.attr("class"));
                        nextSelector.addClass('off').removeClass('show, on').find('td').css('border-bottom','none').find('div').stop().animate({
                            'min-height': '0',
                            'max-height': '0',
                            'padding': '0 10px'
                        },300);
                        if(nextSelector.next().attr("class") != undefined) {
                            nextSelector = nextSelector.next();
                            nextNo = nextSelector.attr("class").replace(/[^0-9]/g, '');
                        }else{
                            break;
                        }
                    }
                    else{
                        break;
                    }
                }

                $(this).parents('tr').removeClass('on');
            }
        });
    });


    var containerWrap = $('.container-wrap');

    //gnb button click
    var menuBtn = $('.menu-btn'),
        menuWrap = $('.menu-wrap');

    menuBtn.on('click',function () {
        if($(this).hasClass('on')){
            $(this).removeClass('on').addClass('off');
            $('.bottom-wrap').removeClass('on').addClass('off');
            removeActive();
        } else {
            $(this).removeClass('off').addClass('on');
            menuWrap.animate({
                'width':'260px'
            },300);
            containerWrap.animate({
                'margin-left':'260px'
            },300);
            $('.bottom-wrap').removeClass('off').addClass('on');
        }
    });

    //gnb li.active
    var gnbLi = $('.tab-list>li'),
        gnbA = $('.tab-list>li>a'),
        dep2 = $('.tab-list .dep2'),
        gnbLiIndex = 0;

    gnbA.on('click',function () {
        gnbLiIndex = $(this).parent('li').index();

        $(this).parent('li').addClass('active').siblings().removeClass('active');
        dep2.stop().slideUp(300);

        if($(this).siblings('.dep2').css('display')=='none'){
            $(this).parent('li').addClass('active');
            menuWrap.animate({
                'width':'260px'
            },300);
            containerWrap.animate({
                'margin-left':'260px'
            },300);
            menuBtn.removeClass('off').addClass('on');
            $(this).siblings('.dep2').slideDown(300);
        } else {
            $(this).parent('li').removeClass('active');
        }

    });

    function removeActive() {
        gnbLi.removeClass('active');
        menuWrap.animate({
            'width':'70px'
        },300);
        containerWrap.animate({
            'margin-left':'70px'
        },300);
        dep2.slideUp();
    }

    //필드 추가
    var trLength = $('.table-cell tbody tr').length;

//    if(trLength < 2){
//        $(this).find('.del-item').css('display','none');
//    }
    $('.add-item').on('click',function () {

        if ($("#itemName").val().replace(/ /gi, "") == null || $("#itemName").val().replace(/ /gi, "") == "") {
            alert("항목명을 입력해주세요.");
            $("#itemName").focus();
            return;
        }
        var special_pattern = /[~!@#$%^&*_+|<>?:{},]/;
        if (special_pattern.test($("#itemName").val()) == true) {
            alert("특수문자는 사용할 수 없습니다.");
            $("#itemName").focus();
            return;
        }
        if($('#itemFlag').val() == 'list'){
            if ($("#itemPercent").val().replace(/ /gi, "") == null || $("#itemPercent").val().replace(/ /gi, "") == "" || $("#itemPercent").val().replace(/ /gi, "") == 0) {
                alert("비율을 입력해주세요.");
                $("#itemPercent").focus();
                return;
            }
            var percent_pattern = /[~!@#$%^&()_+|<>?:{},]/;
            if (percent_pattern.test($("#itemPercent").val()) == true) {
                alert("특수문자는 사용할 수 없습니다.");
                itemPercent_arr[i].focus();
                return;
            }
        }
        var exit= false;
        $("[id^='codeChk']").each(function(index){
            var value = $(this).text();
            if(value.indexOf($("#fieldId option:selected").text()) != -1){
                alert("필드가 중복됩니다.");
                $("#fieldId").focus();
                exit = true;
                return false;
            }
        });
        if(exit){
            return false;
        }

        var trLength = $('.table-cell tbody tr').length,
            num = trLength-1;
        if($('#itemFlag').val() == 'list'){
            if($("input:checkbox[id='mobChkFlag']").is(":checked")){
                if($("input:checkbox[id='searchChkFlag']").is(":checked")){
                    var tr_cell = '<tr><td style="text-align: center;" id="codeChk">' + $("#fieldId option:selected").text() + '<input type="hidden" name="fieldId" value="' + $("#fieldId").val() +'"></td><td style="text-align: center;"><input type="text" name="itemName" maxlength="200" value="' + $("#itemName").val().trim() + '"></td><td style="text-align: center;"><input type="text" name="itemPercent" class="cal30" maxlength="3" value="' + $("#itemPercent").val().trim() + '"> %</td><td style="text-align: center;"><input type="checkbox" id="mobChk'+num+'" onclick="javascript:fn_mobile('+num+');" name="mobFlag" value="Y" checked="checked"></td><td style="text-align: center;"><input type="checkbox" id="searchChk'+num+'" onclick="javascript:fn_searchForm('+num+');" name="searchFlag" value="Y" checked="checked"></td><td style="text-align: center;"><a href="#" onclick="moveUp(this);return false;">▲</a><a href="#" onclick="moveDown(this);return false;">▼</a></td><td style="text-align: center;"><div><button type="button" class="del-item manage-btn delete-btn"><span class="screen-out">삭제</span></button></div></td></tr>'
                }else{
                	if($("#fieldId").val() == "ATCH_FILE_ID" || $("#fieldId").val() == "THUMBNAIL" || $("#fieldId").val() == "START_DATE" || $("#fieldId").val() == "END_DATE" || $("#fieldId").val() == "REG_DATE" || $("#fieldId").val() == "UPD_DATE" || $("#fieldId").val() == "ROWNUM" || $("#fieldId").val() == "RDCNT"){
                        var tr_cell = '<tr><td style="text-align: center;" id="codeChk">' + $("#fieldId option:selected").text() + '<input type="hidden" name="fieldId" value="' + $("#fieldId").val() +'"></td><td style="text-align: center;"><input type="text" name="itemName" maxlength="200" value="' + $("#itemName").val().trim() + '"></td><td style="text-align: center;"><input type="text" name="itemPercent" class="cal30" maxlength="3" value="' + $("#itemPercent").val().trim() + '"> %</td><td style="text-align: center;"><input type="checkbox" id="mobChk'+num+'" onclick="javascript:fn_mobile('+num+');" name="mobFlag" value="Y" checked="checked"></td><td style="text-align: center;"><input type="checkbox" id="searchChk'+num+'" onclick="javascript:fn_searchForm('+num+');" name="searchFlag" value="N" disabled="disabled"></td><td style="text-align: center;"><a href="#" onclick="moveUp(this);return false;">▲</a><a href="#" onclick="moveDown(this);return false;">▼</a></td><td style="text-align: center;"><div><button type="button" class="del-item manage-btn delete-btn"><span class="screen-out">삭제</span></button></div></td></tr>'
                	}else{
                        var tr_cell = '<tr><td style="text-align: center;" id="codeChk">' + $("#fieldId option:selected").text() + '<input type="hidden" name="fieldId" value="' + $("#fieldId").val() +'"></td><td style="text-align: center;"><input type="text" name="itemName" maxlength="200" value="' + $("#itemName").val().trim() + '"></td><td style="text-align: center;"><input type="text" name="itemPercent" class="cal30" maxlength="3" value="' + $("#itemPercent").val().trim() + '"> %</td><td style="text-align: center;"><input type="checkbox" id="mobChk'+num+'" onclick="javascript:fn_mobile('+num+');" name="mobFlag" value="Y" checked="checked"></td><td style="text-align: center;"><input type="checkbox" id="searchChk'+num+'" onclick="javascript:fn_searchForm('+num+');" name="searchFlag" value="N"></td><td style="text-align: center;"><a href="#" onclick="moveUp(this);return false;">▲</a><a href="#" onclick="moveDown(this);return false;">▼</a></td><td style="text-align: center;"><div><button type="button" class="del-item manage-btn delete-btn"><span class="screen-out">삭제</span></button></div></td></tr>'
                	}
                }
            }else{
                if($("input:checkbox[id='searchChkFlag']").is(":checked")){
                    var tr_cell = '<tr><td style="text-align: center;" id="codeChk">' + $("#fieldId option:selected").text() + '<input type="hidden" name="fieldId" value="' + $("#fieldId").val() +'"></td><td style="text-align: center;"><input type="text" name="itemName" maxlength="200" value="' + $("#itemName").val().trim() + '"></td><td style="text-align: center;"><input type="text" name="itemPercent" class="cal30" maxlength="3" value="' + $("#itemPercent").val().trim() + '"> %</td><td style="text-align: center;"><input type="checkbox" id="mobChk'+num+'" onclick="javascript:fn_mobile('+num+');" name="mobFlag" value="N"></td><td style="text-align: center;"><input type="checkbox" id="searchChk'+num+'" onclick="javascript:fn_searchForm('+num+');" name="searchFlag" value="Y" checked="checked"></td><td style="text-align: center;"><a href="#" onclick="moveUp(this);return false;">▲</a><a href="#" onclick="moveDown(this);return false;">▼</a></td><td style="text-align: center;"><div><button type="button" class="del-item manage-btn delete-btn"><span class="screen-out">삭제</span></button></div></td></tr>'
                }else{
                	if($("#fieldId").val() == "ATCH_FILE_ID" || $("#fieldId").val() == "THUMBNAIL" || $("#fieldId").val() == "START_DATE" || $("#fieldId").val() == "END_DATE" || $("#fieldId").val() == "REG_DATE" || $("#fieldId").val() == "UPD_DATE" || $("#fieldId").val() == "ROWNUM" || $("#fieldId").val() == "RDCNT"){
                        var tr_cell = '<tr><td style="text-align: center;" id="codeChk">' + $("#fieldId option:selected").text() + '<input type="hidden" name="fieldId" value="' + $("#fieldId").val() +'"></td><td style="text-align: center;"><input type="text" name="itemName" maxlength="200" value="' + $("#itemName").val().trim() + '"></td><td style="text-align: center;"><input type="text" name="itemPercent" class="cal30" maxlength="3" value="' + $("#itemPercent").val().trim() + '"> %</td><td style="text-align: center;"><input type="checkbox" id="mobChk'+num+'" onclick="javascript:fn_mobile('+num+');" name="mobFlag" value="Y" checked="checked"></td><td style="text-align: center;"><input type="checkbox" id="searchChk'+num+'" onclick="javascript:fn_searchForm('+num+');" name="searchFlag" value="N" disabled="disabled"></td><td style="text-align: center;"><a href="#" onclick="moveUp(this);return false;">▲</a><a href="#" onclick="moveDown(this);return false;">▼</a></td><td style="text-align: center;"><div><button type="button" class="del-item manage-btn delete-btn"><span class="screen-out">삭제</span></button></div></td></tr>'
                	}else{
                        var tr_cell = '<tr><td style="text-align: center;" id="codeChk">' + $("#fieldId option:selected").text() + '<input type="hidden" name="fieldId" value="' + $("#fieldId").val() +'"></td><td style="text-align: center;"><input type="text" name="itemName" maxlength="200" value="' + $("#itemName").val().trim() + '"></td><td style="text-align: center;"><input type="text" name="itemPercent" class="cal30" maxlength="3" value="' + $("#itemPercent").val().trim() + '"> %</td><td style="text-align: center;"><input type="checkbox" id="mobChk'+num+'" onclick="javascript:fn_mobile('+num+');" name="mobFlag" value="Y" checked="checked"></td><td style="text-align: center;"><input type="checkbox" id="searchChk'+num+'" onclick="javascript:fn_searchForm('+num+');" name="searchFlag" value="N"></td><td style="text-align: center;"><a href="#" onclick="moveUp(this);return false;">▲</a><a href="#" onclick="moveDown(this);return false;">▼</a></td><td style="text-align: center;"><div><button type="button" class="del-item manage-btn delete-btn"><span class="screen-out">삭제</span></button></div></td></tr>'
                	}
                }
            }
        }else{
            if($("input:checkbox[id='validationChkFlag']").is(":checked")){
                var tr_cell = '<tr><td style="text-align: center;" id="codeChk">' + $("#fieldId option:selected").text() + '<input type="hidden" name="fieldId" value="' + $("#fieldId").val() +'"></td><td style="text-align: center;"><input type="text" name="itemName" maxlength="200" value="' + $("#itemName").val().trim() + '"></td><td style="text-align: center;"><input type="checkbox" id="validationChk'+num+'" onclick="javascript:fn_validation('+num+');" name="validationFlag" value="Y" checked="checked"></td><td style="text-align: center;"><a href="#" onclick="moveUp(this);return false;">▲</a><a href="#" onclick="moveDown(this);return false;">▼</a></td><td style="text-align: center;"><div><button type="button" class="del-item manage-btn delete-btn"><span class="screen-out">삭제</span></button></div></td></tr>'
            }else{
                var tr_cell = '<tr><td style="text-align: center;" id="codeChk">' + $("#fieldId option:selected").text() + '<input type="hidden" name="fieldId" value="' + $("#fieldId").val() +'"></td><td style="text-align: center;"><input type="text" name="itemName" maxlength="200" value="' + $("#itemName").val().trim() + '"></td><td style="text-align: center;"><input type="checkbox" id="validationChk'+num+'" onclick="javascript:fn_validation('+num+');" name="validationFlag" value="N"></td><td style="text-align: center;"><a href="#" onclick="moveUp(this);return false;">▲</a><a href="#" onclick="moveDown(this);return false;">▼</a></td><td style="text-align: center;"><div><button type="button" class="del-item manage-btn delete-btn"><span class="screen-out">삭제</span></button></div></td></tr>'
            }
        }

        $('.table-cell tbody').append(tr_cell);

//        if(trLength <2){
//            $('.del-item').css('display','none');
//        } else if(trLength>=2){
//            $('.del-item').css('display','inline-block');
//        }

    });

    $('.add-cate').on('click',function () {

        if ($("#cateCode").val().replace(/ /gi, "") == null || $("#cateCode").val().replace(/ /gi, "") == "") {
            alert("카테고리 코드를 입력해주세요.");
            $("#cateCode").focus();
            return;
        }
        var special_pattern = /[~!@#$%^&*+|<>?:{},]/;
        if (special_pattern.test($("#cateCode").val()) == true) {
            alert("특수문자는 사용할 수 없습니다.");
            $("#cateCode").focus();
            return;
        }
        if ($("#cateName").val().replace(/ /gi, "") == null || $("#cateName").val().replace(/ /gi, "") == "") {
            alert("카테고리명을 입력해주세요.");
            $("#cateName").focus();
            return;
        }
        if (special_pattern.test($("#cateName").val()) == true) {
            alert("특수문자는 사용할 수 없습니다.");
            $("#cateName").focus();
            return;
        }
        var exit= false;
        $("[id^='codeChk']").each(function(index){
            var value = $(this).val();
            //var value = $(this).text();
            if(value == $("#cateCode").val()){
                alert("카테고리 코드가 중복됩니다.");
                $("#cateCode").focus();
                exit = true;
                return false;
            }
        });
        if(exit){
            return false;
        }

        var trLength = $('.table-cell tbody tr').length;
        var tr_cell = '<tr><td style="text-align: center;"><input type="text" id="codeChk" name="cateCode" class="wid200" maxlength="200" value="' + $("#cateCode").val().trim() + '"></td><td style="text-align: center;"><input type="text" name="cateName" class="wid200" maxlength="200" value="' + $("#cateName").val().trim() + '"></td><td style="text-align: center;"><a href="#" onclick="moveUp(this);return false;">▲</a><a href="#" onclick="moveDown(this);return false;">▼</a></td><td style="text-align: center;"><div><button type="button" class="del-item manage-btn delete-btn"><span class="screen-out">삭제</span></button></div></td></tr>';

        $('.table-cell tbody').append(tr_cell);

//        if(trLength <2){
//            $('.del-item').css('display','none');
//        } else if(trLength>=2){
//            $('.del-item').css('display','inline-block');
//        }

    });

    $('.add-code').on('click',function () {

        if ($("#codeId").val().replace(/ /gi, "") == null || $("#codeId").val().replace(/ /gi, "") == "") {
            alert("코드 ID를 입력해주세요.");
            $("#codeId").focus();
            return;
        }
        var special_pattern = /[~!@#$%^&*|<>?:{},]/;
        if (special_pattern.test($("#codeId").val()) == true) {
            alert("특수문자는 사용할 수 없습니다.");
            $("#codeId").focus();
            return;
        }
        if ($("#codeName").val().replace(/ /gi, "") == null || $("#codeName").val().replace(/ /gi, "") == "") {
            alert("코드명을 입력해주세요.");
            $("#codeName").focus();
            return;
        }
        if (special_pattern.test($("#codeName").val()) == true) {
            alert("특수문자는 사용할 수 없습니다.");
            $("#codeName").focus();
            return;
        }
        var exit= false;
        $("[id^='codeChk']").each(function(index){
            var value = $(this).val();
            //var value = $(this).text();
            if(value.indexOf($("#codeId").val()) != -1){
                alert("코드 ID가 중복됩니다.");
                $("#codeId").focus();
                exit = true;
                return false;
            }
        });
        if(exit){
            return false;
        }

        var trLength = $('.table-cell tbody tr').length;
        var tr_cell = '<tr><td style="text-align: center;"><input type="text" id="codeChk" name="codeId" class="wid200" maxlength="20" value="' + $("#codeId").val().trim() + '"></td><td style="text-align: center;"><input type="text" name="codeName" class="wid200" maxlength="200" value="' + $("#codeName").val().trim() + '"></td><td style="text-align: center;"><a href="#" onclick="moveUp(this);return false;">▲</a><a href="#" onclick="moveDown(this);return false;">▼</a></td><td style="text-align: center;"><div><button type="button" class="del-item manage-btn delete-btn"><span class="screen-out">삭제</span></button></div></td></tr>';

        $('.table-cell tbody').append(tr_cell);

    });

    $(document).on('click', '.del-item', function () {
        var $tbl_wrap = $(this).parents('.tbl-wrap');
        var cell = $tbl_wrap.find('.tbl03 tbody');
        var delLength = cell.find('.del-item').length -1;

        $(this).parents('tr').remove();

//        if(delLength <2){
//            cell.find('.del-item').css('display','none');
//        }

    });
});