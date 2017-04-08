(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('serviceclearanceaction', {
            parent: 'app',
            url: '/serviceclearanceaction/{id}',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/serviceclearanceactions/serviceclearanceactions.html',
                    controller: 'serviceClearanceActionController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('home');
                    return $translate.refresh();
                }],
                projectservicelist: ['$stateParams','ProjectservicedetailbyProject', function($stateParams,ProjectservicedetailbyProject) {
                    return ProjectservicedetailbyProject.query({id : $stateParams.id}).$promise;
                }]
            }
        })
        .state('assignservice', {
            parent: 'app',
            url: '/assignservice',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/serviceclearanceactions/assignServiceDialog.html',
                    controller: 'assignServiceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        projectservicedetail: function () {
                            return {
                                projectid: null,
                                serviceid: null,
                                isRequired: null,
                                requireMarkedOnDate: null,
                                requireMarkedBy: null,
                                isAssigned: null,
                                assigOnDate: null,
                                assignBy: null,
                                formFilledStatus: null,
                                isPaymentMade: null,
                                isPaymentVerified: null,
                                formFilledOnDate: null,
                                formFilledBy: null,
                                paymentMadeOnDate: null,
                                status: null,
                                latestComments: null,
                                serviceFee: null,
                                id: null
                            };
                        },
                        projectAttachemnt: function () {
                              return {
                                  fileName: null,
                                  description: null,
                                  fileExtension: null,
                                  serverFileName: null,
                                  id: null
                              };
                          },
                          projectServiceLog: function () {
                              return {
                                  projectid: null,
                                  serviceid: null,
                                  comments: null,
                                  commentDate: null,
                                  commentByUserLogin: null,
                                  id: null
                              };
                          },
                          projectService: ['$stateParams', function($stateParams) {
                              return $stateParams.projectService;
                          }],
                        translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                            $translatePartialLoader.addPart('home');
                            return $translate.refresh();
                        }]
                    }
                }).result.then(function() {
                    $state.go('serviceclearanceaction', null, { reload: 'serviceclearanceaction' });
                }, function() {
                    $state.go('serviceclearanceaction');
                });
            }]
        })

    .state('serviceForm-fillform', {
        parent: 'app',
        url: '/fillform',
        data: {
            authorities: ['ROLE_USER']
        },
        onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
            $uibModal.open({
                templateUrl: 'app/serviceclearanceactions/serviceForm.html',
                controller: 'serviceFormController',
                controllerAs: 'vm',
                backdrop: 'static',
                size: 'lg',
                resolve: {
                    entity: function () {
                        return {
                            countryname: null,
                            id: null
                        };
                    },
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('home');
                        return $translate.refresh();
                    }]
                }
            }).result.then(function() {
                $state.go('serviceclearanceaction', null, { reload: 'serviceclearanceaction' });
            }, function() {
                $state.go('serviceclearanceaction');
            });
        }]
    })

  .state('servicePayment', {
          parent: 'app',
          url: '/payFee',
          data: {
              authorities: ['ROLE_USER']
          },
          onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
              $uibModal.open({
                  templateUrl: 'app/serviceclearanceactions/servicePayment.html',
                  controller: 'servicePaymentController',
                  controllerAs: 'vm',
                  backdrop: 'static',
                  size: 'lg',
                  resolve: {
                      entity: function () {
                          return {
                              projectid: null,
                              serviceid: null,
                              isRequired: null,
                              requireMarkedOnDate: null,
                              requireMarkedBy: null,
                              isAssigned: null,
                              assigOnDate: null,
                              assignBy: null,
                              formFilledStatus: null,
                              isPaymentMade: null,
                              isPaymentVerified: null,
                              formFilledOnDate: null,
                              formFilledBy: null,
                              paymentMadeOnDate: null,
                              status: null,
                              latestComments: null,
                              serviceFee: null,
                              id: null
                          };
                      },
                      translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                          $translatePartialLoader.addPart('home');
                          return $translate.refresh();
                      }]
                  }
              }).result.then(function() {
                  $state.go('serviceclearanceaction', null, { reload: 'serviceclearanceaction' });
              }, function() {
                  $state.go('serviceclearanceaction');
              });
          }]
      })

  .state('verifyPayment', {
            parent: 'app',
            url: '/verify',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/serviceclearanceactions/verifyPayment.html',
                    controller: 'verifyPaymentController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
//                        entity: function () {
//                            return {
//                                countryname: null,
//                                id: null
//                            };
//                        },
                        translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                            $translatePartialLoader.addPart('home');
                            return $translate.refresh();
                        }]
                    }
                }).result.then(function() {
                    $state.go('serviceclearanceaction', null, { reload: 'serviceclearanceaction' });
                }, function() {
                    $state.go('serviceclearanceaction');
                });
            }]
        })


  .state('serviceClearance-action', {
          parent: 'app',
          url: '/action',
          data: {
              authorities: ['ROLE_USER']
          },
          onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
              $uibModal.open({
                  templateUrl: 'app/serviceclearanceactions/serviceclearance.html',
                  controller: 'serviceClearanceController',
                  controllerAs: 'vm',
                  backdrop: 'static',
                  size: 'lg',
                  resolve: {
                       projectAttachemnt: function () {
                            return {
                                fileName: null,
                                description: null,
                                fileExtension: null,
                                serverFileName: null,
                                id: null
                            };
                       },
                       projectServiceLog: function () {
                            return {
                                projectid: null,
                                serviceid: null,
                                comments: null,
                                commentDate: null,
                                commentByUserLogin: null,
                                id: null
                            };
                       },
                      translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                          $translatePartialLoader.addPart('home');
                          return $translate.refresh();
                      }]
                  }
              }).result.then(function() {
                  $state.go('serviceclearanceaction', null, { reload: 'serviceclearanceaction' });
              }, function() {
                  $state.go('serviceclearanceaction');
              });
          }]
      })
  .state('deemedClearance', {
            parent: 'app',
            url: '/deemed',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/serviceclearanceactions/deemedClearance.html',
                    controller: 'deemedClearanceController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        projectAttachemnt: function () {
                            return {
                              fileName: null,
                              description: null,
                              fileExtension: null,
                              serverFileName: null,
                              id: null
                        };
                        },
                        projectServiceLog: function () {
                            return {
                              projectid: null,
                              serviceid: null,
                              comments: null,
                              commentDate: null,
                              commentByUserLogin: null,
                              id: null
                            };
                        },
                        translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                            $translatePartialLoader.addPart('home');
                            return $translate.refresh();
                        }]
                    }
                }).result.then(function() {
                    $state.go('serviceclearanceaction', null, { reload: 'serviceclearanceaction' });
                }, function() {
                    $state.go('serviceclearanceaction');
                });
            }]
        })
  .state('comment-dialog', {
              parent: 'serviceclearanceaction',
              url: '/comment/{projectService:json}',
              data: {
                  authorities: ['ROLE_USER']
              },
              onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                  $uibModal.open({
                      templateUrl: 'app/serviceclearanceactions/commentDialog.html',
                      controller: 'commentDialogController',
                      controllerAs: 'vm',
                      backdrop: 'static',
                      size: 'lg',
                      resolve: {
                          projectAttachemnt: function () {
                              return {
                                  fileName: null,
                                  description: null,
                                  fileExtension: null,
                                  serverFileName: null,
                                  id: null
                              };
                          },
                          projectServiceLog: function () {
                              return {
                                  projectid: null,
                                  serviceid: null,
                                  comments: null,
                                  commentDate: null,
                                  commentByUserLogin: null,
                                  id: null
                              };
                          },
                          translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                              $translatePartialLoader.addPart('home');
                              return $translate.refresh();
                          }]
                      }
                  }).result.then(function() {
                      $state.go('serviceclearanceaction', null, { reload: 'serviceclearanceaction' });
                  }, function() {
                      $state.go('serviceclearanceaction');
                  });
              }]
          })
          .state('reject-dialog', {
                parent: 'app',
                url: '/reject',
                data: {
                    authorities: ['ROLE_USER']
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'app/serviceclearanceactions/rejectDialog.html',
                        controller: 'rejectDialogController',
                        controllerAs: 'vm',
                        backdrop: 'static',
                        size: 'lg',
                        resolve: {
                            projectAttachemnt: function () {
                                return {
                                    fileName: null,
                                    description: null,
                                    fileExtension: null,
                                    serverFileName: null,
                                    id: null
                                };
                            },
                            projectServiceLog: function () {
                                return {
                                    projectid: null,
                                    serviceid: null,
                                    comments: null,
                                    commentDate: null,
                                    commentByUserLogin: null,
                                    id: null
                                };
                            },
                            translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                                $translatePartialLoader.addPart('home');
                                return $translate.refresh();
                            }]
                        }
                    }).result.then(function() {
                        $state.go('serviceclearanceaction', null, { reload: 'serviceclearanceaction' });
                    }, function() {
                        $state.go('serviceclearanceaction');
                    });
                }]
            })

    }
})();
