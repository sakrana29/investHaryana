(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('viewprojectdetailController', addprojectController);

    addprojectController.$inject = ['$scope', 'Principal', 'LoginService', '$state', 'Projectlist','Pendingprojectlist','Completedprojectlist'];

    function addprojectController ($scope, Principal, LoginService, $state, Projectlist,Pendingprojectlist,Completedprojectlist) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;

        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        function register () {
            $state.go('register');
        }

        vm.projectcompletedetail=[];


         loadAll();
         vm.loadAll=loadAll;
         function loadAll() {

             Projectlist.query(function(result){
             vm.projectlist=result;
             });
         }

         vm.loadPending=loadPending;
            function loadPending(){
            //alert('Pending List');
            Pendingprojectlist.query({cafpin: "Pending"}, function(data){
                 console.log(data);
                 vm.projectlist=data;
             });
         }

         vm.loadCompleted=loadCompleted;
            function loadCompleted(){
           // alert('Completed List')
            Completedprojectlist.query({cafpin: "292929"}, function(data){
            console.log(data);
            vm.projectlist=data;
             });
          }


    }
})();
