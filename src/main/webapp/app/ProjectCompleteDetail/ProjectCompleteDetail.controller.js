(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectCompleteDetailController', ProjectCompleteDetailController);

    ProjectCompleteDetailController.$inject = ['$scope', 'Principal', 'LoginService', '$state','entity','Projectcompletedetail','Projectcompletedetaildata'];

    function ProjectCompleteDetailController ($scope, Principal, LoginService, $state, entity, Projectcompletedetail,Projectcompletedetaildata) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.CompleteProjectDetailData=entity;

        vm.investor=vm.CompleteProjectDetailData.investorDTO;
        vm.companydetail=vm.CompleteProjectDetailData.companydetailDTO;
        vm.projectdetail=vm.CompleteProjectDetailData.projectdetailDTO;
        vm.projectsitedetail=vm.CompleteProjectDetailData.projectsitedetailDTO;
        vm.project_finance_investment=vm.CompleteProjectDetailData.project_finance_investmentDTO;
        vm.manufacturing_detail=vm.CompleteProjectDetailData.manufacturingdetailDTO;
        vm.electricrequirement=vm.CompleteProjectDetailData.electricrequirementDTO;
        vm.projectcombinecodes=vm.CompleteProjectDetailData.projectdetailcombinecodesDTO;

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
    }
})();
