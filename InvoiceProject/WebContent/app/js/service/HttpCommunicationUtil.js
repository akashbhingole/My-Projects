var HttpCommunicationUtil = function($http, $rootScope) {
	var factory = {};

	factory.doGet = function(url, successCB, errorCB){
		return $http.get(url).success(function(data, status, headers, config){
		    successCB(data);
		})
		.error(function(data, status, headers, config){
			errorCB(data);
		});
	};
	
	factory.doPost = function(url, data, successCB, errorCB){
		return $http.post(url, data).success(function(data, status, headers, config){
			successCB(data);
		})
		.error(function(data, status, headers, config){
			errorCB(data);
		});
	},

	factory.doDelete = function(url, successCB, errorCB){
		return $http.delete(url).success(function(data, status, headers, config){
		    successCB(data);
		})
		.error(function(data, status, headers, config){
			errorCB(data);
		});
	};

	return factory;
}

IGApp.factory('HttpCommunicationUtil', HttpCommunicationUtil);
