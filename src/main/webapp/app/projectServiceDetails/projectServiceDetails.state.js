(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('projectServiceDetails', {
            parent: 'app',
            url: '/Project/{id}',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/projectServiceDetails/projectServiceDetails.html',
                    controller: 'projectServiceDetailsController',
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
                }],

                projectservicedetailentity: ['$stateParams', function ($stateParams) {
                    //console.log($stateParams.projectid);
                        return {
                            projectid: $stateParams.id,
                            serviceid: null,
                            departmentname: null,
                            servicename: null,
                            isrequired: null,
                            markrequiredondate: null,
                            markrequiredby: null,
                            isassigned: null,
                            markassignedby: null,
                            feerequired: null,
                            status: null,
                            comment: null,
                            id: null
                        };
                   }]
            }
        })

        .state('projectServiceDetails-fee', {
                      parent: 'app',
                                    url: '/Fee/:id?serviceid?projectid',
                                    data: {
                                        authorities: []
                                    },
                      onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                      $uibModal.open({

                      templateUrl: 'app/projectServiceDetails/projectServiceDetails-Fee.html',
                        controller: 'projectServiceDetailsFeeController',
                        controllerAs: 'vm',
                      backdrop: 'static',
                       size: 'lg',

                      resolve: {
                        projectservicedetailbyidentity: ['Projectservicedetail', function(Projectservicedetail) {
                            console.log($stateParams.id);
                                return Projectservicedetail.get({id : $stateParams.id}).$promise;
                            }],
                            entity: ['$stateParams', function ($stateParams) {
                            //console.log($stateParams.projectid);
                                return {
                                    projectid:null,
                                    serviceid:null,
                                    departmentname: null,
                                    servicename: null,
                                    isrequired: null,
                                    markrequiredondate: null,
                                    markrequiredby: null,
                                    isassigned: null,
                                    markassignedby: null,
                                    feerequired: null,
                                    status: null,
                                    comment: null,
                                    id: $stateParams.id
                                };
                           }]
                    }

               }).result.then(function() {
                                               $state.go('projectServiceDetails', null, { reload: 'projectServiceDetails' });
                                           }, function() {
                                               $state.go('projectServiceDetails');
                                           });
               }]
               })

          .state('serviceForm-fillform', {
                      parent: 'projectServiceDetails',
                      url: '/fillform/:serviceID?projectid',
                      data: {
                          authorities: ['ROLE_USER']
                      },
                      onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                          $uibModal.open({
                              templateUrl: 'app/projectServiceDetails/serviceForm.html',
                              controller: 'fillformController',
                              controllerAs: 'vm',
                              backdrop: 'static',
                              size: 'lg'
//                              resolve: {
//                                    entity: ['$stateParams', 'ServiceFormField', function($stateParams,ServiceFormField) {
//                                          return ServiceFormField.get({id : $stateParams.id}).$promise;
//                                  }]
//                              }
                          }).result.then(function() {
                              $state.go('projectServiceDetails', null, { reload: 'projectServiceDetails' });
                          }, function() {
                              $state.go('projectServiceDetails');
                          });
                      }]
                  })
//          .state('assignservicetoproject-clearance', {
//                        parent: 'assignservicetoproject',
//                        url: '/serviceclearance/:servicename?projectid',
//                        data: {
//                            authorities: ['ROLE_USER']
//                        },
//                        onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
//                            $uibModal.open({
//                                templateUrl: 'app/assignservicetoproject/serviceclearance.html',
//                                controller: 'serviceclearanceController',
//                                controllerAs: 'vm',
//                                backdrop: 'static',
//                                size: 'lg'
//
//            //                    resolve: {
//            //                        entity: function () {
//            //                            return {
//            //                                districtid: null,
//            //                                blockname: null,
//            //                                id: null
//            //                            };
//            //                        }
//            //                    }
//                            }).result.then(function() {
//                                $state.go('assignservicetoproject', null, { reload: 'projectServiceDetails' });
//                            }, function() {
//                                $state.go('assignservicetoproject');
//                            });
//                        }]
//                    });


    }
})();
