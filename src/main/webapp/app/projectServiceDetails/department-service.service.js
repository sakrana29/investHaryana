(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('DepartmentServiceProjectWise', DepartmentServiceProjectWise);

    DepartmentServiceProjectWise.$inject = ['$resource'];

    function DepartmentServiceProjectWise ($resource) {
//    $resourceProvider.defaults.stripTrailingSlashes = false;
        var resourceUrl =  'api/ProjectWiseServiceDetails/:projectid';
        var dataarray ={};
//        resourceUrl.defaults.stripTrailingSlashes = false;
        return $resource(resourceUrl, {projectid:'@id'}, {
                    'query': { method: 'GET', isArray: true},
                    'get': {
                        method: 'GET',
                        transformResponse: function (data, headers) {
                            if (data) {
                                data = angular.fromJson(data);
                            }

                            return data;
                        }
                    },
                    'update': { method:'PUT' }
                });
    }
})();
