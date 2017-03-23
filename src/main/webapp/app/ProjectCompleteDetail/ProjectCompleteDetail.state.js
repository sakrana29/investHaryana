(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('ProjectCompleteDetailState', {
            parent: 'app',
            url: '/ProjectCompleteDetail/{projectid}',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/ProjectCompleteDetail/ProjectCompleteDetail.html',
                    controller: 'ProjectCompleteDetailController',
                    controllerAs: 'vm'
                }
            },
              resolve: {
                  translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                      $translatePartialLoader.addPart('home');
                      return $translate.refresh();
                  }]
              }
        });
    }
})();
