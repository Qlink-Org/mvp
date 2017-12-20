(function(app){
	app.controller('PayController',['$scope',function($scope){
		$scope.payData = [
		{val:'50元'},
		{val:'100元'},
		{val:'200元'},
		{val:'500元'},
		{val:'1000元'},
		{val:'其他金额'}
		]
	}]);
	app.controller('RegisterForm',['$scope','$interval',function($scope,$interval){
		$scope.paracont = "获取验证码";  
      	$scope.paraclass = '';  
       	var second = 60,  
       	timePromise = undefined;  
  		$scope.verification = function(){
  			if ( angular.isDefined(timePromise) ) return;
	        timePromise = $interval(function(){  
	          if(second<=0){  
	            $interval.cancel(timePromise);  
	            timePromise = undefined;
	    	    second = 60;  
	            $scope.paracont = "重发验证码";  
	            $scope.paraclass = "";  
	          }else{  
	            $scope.paracont = second + "秒后可重发";  
	            $scope.paraclass = "intime";  
	            second--;  
	             
	          }  
	        },1000,100); 
	    }
	}])
	app.controller('devController',['$scope',function($scope){
		$scope.deviceShow = false;
		$scope.Devclose = function(){
			$scope.deviceShow = false;
		}
		$scope.Devopen = function(){
			$scope.deviceShow = true;
		}
	}])
}(angular.module('myProduct')))
