$(document).ready(function () {
  $('#wrap').css('overflow', 'hidden');
  $('.box').addClass('on');

  // 첫 화면
  setTimeout(function() {
    $('.message_area:nth-child(1)').addClass('active');
  }, 520);
  
  // yes 버튼 클릭 후
  $('.message_area:nth-child(1) .btn_yes').on('click touchstart', function () {
    $(this).parents('.message_area').next().addClass('active');
    
    var offset = $('.new_message_area:nth-child(2)').offset().top;
    
    $('html,body').animate({
      scrollTop:( offset - 500 )
    }, 770);

  });
  
  // 음료 선택 input
  $('.drink_con').on('click touchstart', function () {
    if ($(this).prev().is(':checked')) {
      $(this).prev().prop('checked', false);
    } else {
      $(this).prev().prop('checked', true);
    }

    // 음료 재 선택 시 : 이하 모든 active 제거
    var newM = $(this).parents('.new_message_area');
    if(newM.next().hasClass('active') === true){
      newM.nextAll().removeClass('active');
      // var dList = newM.next('.active');
      
      // $dList.each(function (index) {
      //   let v = (index + 1);
      //   $dList.eq(v).removeClass('active');
      // })

      // for (var idx = 0; idx < $('.active').length; idx++) {
      //   var d = $('.active').eq(idx);
      //   d.removeClass('active');
      // }
    }

  });

  // 음료 선택 후 버튼
  $('.new_message_area .btn_yes').on('click touchstart', function () {
	if($('#currentPrice').val()*1 <= 0) {
		alert('음료를 선택해주세요.');
		return false;
	}
    $(this).parents('.new_message_area').next().addClass('active');
    $('#wrap').css('padding-bottom', '50rem');

    var offset = $('.message_area:nth-child(3)').offset().top;
    
    $('html').animate({
      scrollTop:( offset)
    }, 1400);

    setTimeout(function() {
      $('.payment').addClass('active');
    }, 1520);

  });

  // 결제 클릭
  $('.payment ul li a').on('click touchstart', function () {
    $(this).parents('.payment').next().addClass('active');

    var offset = $('.message_area:nth-child(5)').offset().top;
    
    $('html').animate({
      scrollTop:( offset - 700 )
    }, 1400);

    // 배너 이미지 
    setTimeout(function() {
      $('.banner').addClass('active');

      var offsetB = $('.banner').offset().top;

      $('html').animate({
        scrollTop:( offsetB - 800 )
      }, 900);

    }, 2200);

    // 결제 완료 , 음료 수거 메시지
    setTimeout(function() {
      $('.last').addClass('active');
      $('#wrap').css('padding-bottom', '');

      var offsetL = $('.last').offset().top;

      $('html').animate({
        scrollTop:( offsetL - 1300 )
      }, 800);

    }, 4000);

  });


});
