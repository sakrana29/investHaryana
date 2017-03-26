(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('listprojectController', listprojectController);

    listprojectController.$inject = ['$scope','$state', 'Projectcompletedetail','Projectcompletedetaildata'];

    function listprojectController($scope, $state, Projectcompletedetail,Projectcompletedetaildata) {
        var vm = this;
        vm.projectcompletedetail=[];

        loadAll();

        function loadAll() {

            Projectcompletedetaildata.query(function(result){
                vm.projectcompletedetail=result;
//                console.log(vm.projectcompletedetail[0].projectdetailDTO);
            });


        }


//        $http.get("/api/projectdetails").then(function(response) {
//           vm.prdetail = response.data;
//       });
    }
})();
