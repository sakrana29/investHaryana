(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('FileManagement',['$http', function($http){
        return {
                saveFile: function(file,filename){
                        var resourceUrl =  'api/FileManagement';
                        var fd = new FormData();
                        fd.append('file', file);
                        fd.append('filename',filename);
                        $http.post(resourceUrl, fd, {
                        transformRequest : angular.identity,
                        headers : {
                        'Content-Type' : undefined
                        }
                        }).success(function() {
                        console.log('success');
                        }).error(function() {
                        console.log('error');
                        });
                }
//                return 'success';
            }
        }]);








//    var resourceUrl =  'api/FileManagement';
//                $http.post(resourceUrl, fd, {
//                transformRequest : angular.identity,
//                headers : {
//                'Content-Type' : undefined
//                }
//                }).success(function() {
//                console.log('success');
//                }).error(function() {
//                console.log('error');
//                });





//    function FileManagement ($resource) {
//        var resourceUrl =  'api/FileManagement';
//
//        return $resource(resourceUrl, {}, {
//            'query': { method: 'GET', isArray: true},
//            'get': {
//                method: 'GET',
//                transformResponse: function (data) {
//                    if (data) {
//                        data = angular.fromJson(data);
//                    }
//                    return data;
//                }
//            },
//            'update': { method:'PUT' }
//        });
//    }
})();
