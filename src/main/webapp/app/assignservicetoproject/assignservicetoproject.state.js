(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('assignservicetoproject', {
            parent: 'app',
            url: '/assignservicetoproject/{id}',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/assignservicetoproject/assignservicetoproject.html',
                    controller: 'assignservicetoprojectController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('home');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Projectcompletedetail', function($stateParams,Projectcompletedetail) {
                        return Projectcompletedetail.get({id : $stateParams.id}).$promise;
                }]
            }
        })
        .state('processapplication-fee', {
                      parent: 'assignservicetoproject',
                      url: '/process/:serviceid?projectid',
                      data: {
                          authorities: []
                      },
                      onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                          $uibModal.open({
                             templateUrl: 'app/assignservicetoproject/paymentservice.html',
                              controller: 'paymentserviceController',
                              controllerAs: 'vm',
                              backdrop: 'static',
                              size: 'lg',

                          resolve: {
                                entity: ['$stateParams', function ($stateParams) {
                                //console.log($stateParams.projectid);
                                    return {

                                        projectid: $stateParams.projectid,
                                        serviceid: $stateParams.serviceid,
                                        userlogin: null,
                                        servicerequired: true,
                                        servicestatus: true,
                                        assigndate: null,
                                        servicefee: null,
                                        remarks: null,
                                        id: null
                                    };
                               }]
                            }
                          }).result.then(function() {
                              $state.go('assignservicetoproject', null, { reload: 'assignservicetoproject' });
                          }, function() {
                              $state.go('assignservicetoproject');
                          });
                      }]

                  });
    }
})();





