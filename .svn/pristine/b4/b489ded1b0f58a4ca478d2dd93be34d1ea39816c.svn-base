;(function(){
	var app = angular.module('itemPay',[]);
	app.directive('itemPay',[function(){
		return {
			restrict:'A',
//			scope:{
//				payData:'='
//			},
			template:'<div class="pay-select clearfix">'
			+'<span class="pay-money" ng-repeat="item in payData" ng-click="spanClick($index,$event)">{{item.val}}</span>'
			+'</div>',
			replace:true,
			link:function(scope,element,attrs){
				var payData = scope.payData;
				scope.spanClick = function(index,e){
					var toElement = $(e.currentTarget);
					var sibElement  = toElement.siblings();
					sibElement.removeClass('active');
					sibElement.removeClass('act-pay');
					if(payData[index].val=='其他金额'){
						toElement.addClass('act-pay');
						toElement.removeClass('active');
						toElement.attr('contenteditable', true);
						toElement.focus();
                        toElement.html('');
                        toElement.bind('blur', function () {
                        	toElement.attr('contenteditable', false);
	                        if (toElement.html() == '' || toElement.html() == '其他金额') {  
	                            toElement.html('其他金额');
	                            toElement.removeClass('act-pay');
        						toElement.removeClass('active');
	                        } else {
	                            paynum = parseFloat(toElement.html());
	                            if (!isMoney(paynum) || paynum==0 || paynum>999999) {
		                            toElement.html('其他金额');
	        						toElement.removeClass('active');
	                            } else {
	                            	toElement.html(paynum + '元');
	        						toElement.addClass('active');
	                            }
	                            toElement.removeClass('act-pay');
	                        }
                        });
                        element.bind('keydown', function (event) {
		                    var esc_key = event.which === 27,
		                        enter_key = event.which === 13;
		
		                    if (esc_key || enter_key) {
		                        event.preventDefault();
		                        toElement.blur();
		                    }
		                });
					}else{
						toElement.addClass('active')
					}
				}
			}
		}
	}]);
}())
