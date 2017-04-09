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
                }]
            }
        })
        .state('assignservice', {
            parent: 'serviceclearanceaction',
            url: '/assignservice/{projectService:json}',
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

    .state('serviceForm-fillform', {
        parent: 'serviceclearanceaction',
        url: '/fillform/{projectService:json}',
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
                    projectServiceFormFieldData: function () {
                        return {
                            serviceid: null,
                            formfieldvalue: null,
                            projectid: null,
                            formfieldName: null,
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
          parent: 'serviceclearanceaction',
          url: '/payFee/{projectService:json}',
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
                      projectServicePayment: function () {
                          return {
                              projectid: null,
                              serviceid: null,
                              paymentMade: null,
                              paymentMadeBy: null,
                              paymentDate: null,
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
            parent: 'serviceclearanceaction',
            url: '/verify/{projectService:json}',
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
          parent: 'serviceclearanceaction',
          url: '/clear/{projectService:json}',
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
            parent: 'serviceclearanceaction',
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
                parent: 'serviceclearanceaction',
                url: '/reject/{projectService:json}',
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
