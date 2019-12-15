var app = angular.module('myapp', []);
/*
 * app.controller('mycontroller', function($scope, $http) { $scope.pageNumber =
 * 0; $scope.products = [];
 * 
 * $scope.getProducts = function() { $http.post('shop/' +
 * $scope.pageNumber).then(function(result) { console.log("bat dau tra ve");
 * $scope.products = result.data; console.log("sau khi gan"); }); } });
 */

// /////////////////////////////////////////////////////////////////////////////
// /////////////////////////// ////////////////////////////////
// /////////////////// Thao tác với giỏ hàng //////////////////////
// ////////////////////////// ///////////////////////////////
// //////////////////////////////////////////////////////////////////////////
app.controller('cartController', function($scope, $http) {

	$scope.productDetailId = -1;
	$scope.amount = -1;
	$scope.carts = [];
	$scope.cartTotal = 0;

	$scope.getCart = function() {
		$http.get('cart/get-carts').then(function(result) {

			$scope.carts = result.data;

			var t = 0;
			for (var i = 0; i < $scope.carts.length; i++) {
				t += $scope.carts[i].totalPriceUnFormat;
			}

			$scope.cartTotal = t;

		});
	};

	$scope.updateCart = function(index) {
		var id = $scope.carts[index].id;
		var amount = $scope.carts[index].amountSale;

		$http.get('cart/update?id=' + id + '&amount=' + amount).then(
				function(result) {
					console.log(result);

				});
	};

	$scope.changeAmount = function(amount, index) {
		if (amount < 1) {
			$scope.carts[index].amountSale = 1;
		} else {
			$scope.carts[index].amountSale = amount;
		}
		$scope.updateCart(index);
	};

	$scope.delCart = function(index) {
		if (confirm('Chắc chắn xóa?')) {
			var id = $scope.carts[index].id;
			$http.get('cart/delete?id=' + id).then(function(result) {
				$scope.carts.splice(index, 1);
			});
		}
	};

});

// --------------------------------------------------------------------------------------------------

// /////////////////////////////////////////////////////////////////////////////
// ///////////////////// //////////////////////////
// ////////// Thao tác với bộ lọc và sắp xếp ///////////////////
// ///////////////////// /////////////////////////
// //////////////////////////////////////////////////////////////////////////
app.controller('filterController', function($scope, $http) {

	$scope.typeSort = 1;
	$scope.field = 'price';
	$scope.reverse = false;
	$scope.products = [];
	$scope.pageDetail = {};
	$scope.pageCurrent = 1;
	$scope.maxPage = 1;
	$scope.pageStart = 1;
	$scope.pageEnd = 1;
	$scope.categories = [];
	$scope.size = [];
	$scope.minPrice = 0;
	$scope.maxPrice = -1;
	$scope.rangePage = [ 1 ];

	// --------------------------------------------
	// Hàm lấy dữ liệu từ Controller
	// --------------------------------------------
	$scope.fillter = function() {
		$http.get(
				'shop/fillter?page=' + ($scope.pageCurrent - 1)
						+ '&categories=' + $scope.categories + '&priceMin='
						+ $scope.minPrice + '&priceMax=' + $scope.maxPrice
						+ '&sizes=' + $scope.size).then(function(result) {
			$scope.products = result.data.pageList;
			$scope.pageDetail = result;
			$scope.maxPage = result.data.pageCount;
			if ($scope.maxPage <= 10) {
				$scope.pageEnd = $scope.maxPage;
			} else {
				$scope.pageEnd = 10;
			}

			// rangePage là mảng các trang sẽ được in ra
			console.log(result);
			console.log("maxpage: " + $scope.maxPage);
			var input = [];
			for (var i = $scope.pageStart; i <= $scope.pageEnd; i++) {
				input.push(i);
			}
			$scope.rangePage = input;

		});
	};

	// ---------------------------------------------------------
	// Hàm sắp xếp
	// ----------------------------------------------------------
	$scope.sort = function(fieldsort, isASC) {
		$scope.field = fieldsort;
		$scope.reverse = isASC;
	};

	$scope.change = function() {
		if ($scope.typeSort == 1) {
			$scope.sort('id', true);
		} else if ($scope.typeSort == 2) {
			$scope.sort('name', false);
		} else if ($scope.typeSort == 3) {
			$scope.sort('name', true);
		} else if ($scope.typeSort == 4) {
			$scope.sort('realPriceUnFormat', false);
		} else if ($scope.typeSort == 5) {
			$scope.sort('realPriceUnFormat', true);
		}
	};

	// ---------------------------------------------------------
	// Hàm lấy thông tin từ bộ lọc
	// ----------------------------------------------------------
	$scope.getFillter = function() {
		// danh muc
		var cats = document.getElementsByClassName("danh-muc");
		$scope.categories = [];
		for (i = 0; i < cats.length; i++) {
			if (cats[i].checked == true
					&& !$scope.categories.includes(cats[i].value)) {
				$scope.categories = $scope.categories.concat(cats[i].value);
			}
		}

		// size
		var sizes = document.getElementById("sizeSelect");
		$scope.size = [];
		for (i = 0; i < sizes.length; i++) {
			if (sizes[i].selected == true
					&& !$scope.size.includes(sizes[i].value)) {
				$scope.size = $scope.size.concat(sizes[i].value);
			}
		}

		// min max
		var max = document.getElementById("priceMax").value;
		var min = document.getElementById("priceMin").value;
		if (min >= 0 && max > min) {
			$scope.minPrice = min;
			$scope.maxPrice = max;
		}
		$scope.pageCurrent = 1;
		$scope.fillter();
	};

	// ---------------------------------------------------------
	// Xử lý phân trang
	// ----------------------------------------------------------
	$scope.handlPage = function() {

	};

	$scope.changePage = function(page) {
		if (page < 1) {
			$scope.pageCurrent = 1;
		} else if (page > $scope.maxPage) {
			$scope.pageCurrent = $scope.maxPage;
		} else {
			$scope.pageCurrent = page;
		}
		var beHon = 0;
		var lonHon = 0;
		if ($scope.pageCurrent < $scope.pageStart && $scope.pageCurrent >= 1) {
			beHon = $scope.pageStart - $scope.pageCurrent;
			$scope.changePageStartAndEnd($scope.pageStart - beHon,
					$scope.pageEnd - beHon);
		} else if ($scope.pageCurrent > $scope.pageEnd
				&& $scope.pageCurrent <= $scope.maxPage) {
			lonHon = $scope.pageCurrent - $scope.pageEnd;
			$scope.changePageStartAndEnd($scope.pageStart + lonHon,
					$scope.pageEnd + lonHon);
		}

		$scope.fillter();
	};

	$scope.changePageStartAndEnd = function(pageStart, pageEnd) {
		$scope.pageStart = pageStart;
		$scope.pageEnd = pageEnd;
	};
});

// --------------------------------------------------------------------------------------------------------

// /////////////////////////////////////////////////////////////////////////////
// ///////////////////// //////////////////////////
// //////////Xử lý comment ///////////////////
// ///////////////////// /////////////////////////
// //////////////////////////////////////////////////////////////////////////
app
		.controller(
				'HandleComment',
				function($scope, $http) {
					$scope.comments = [];

					$scope.getcm = function(productId) {
						$http.get('comment/get-comment?productId=' + productId)
								.then(function(result) {
									console.log(result.data);
									$scope.comments = result.data;
								});
					};

					$scope.visibleReplyForm = function(i) {
						var accId = document.getElementById("replyForm" + i).children[0].elements[0].value;
						if (accId == "") {
							alert("Vui lòng đăng nhập để bình luận!");
							return null;
						}

						var rf = document.getElementsByClassName("reply-form");
						for (var r = 0; r < rf.length; r++) {
							if (r == i) {
								rf[r].style.display = "block";
							} else {
								rf[r].style.display = "none";
							}
						}
					};

					$scope.setReplyForm = function(cmId, i, accName) {
						document.getElementById("commentParentID" + i).value = cmId;
						document.getElementById("content" + i).innerHTML = accName
								+ ": ";
						$scope.visibleReplyForm(i);
					};

					$scope.saveComment = function(i) {
						var accId = document.getElementById("replyForm" + i).children[0].elements[0].value;

						if (accId == "") {
							alert("Vui lòng đăng nhập để bình luận!");
							return null;
						}

						var productId = document
								.getElementById("replyForm" + i).children[0].elements[1].value;
						var parentId = document.getElementById("replyForm" + i).children[0].elements[2].value;
						var content = document.getElementById("replyForm" + i).children[0].elements[3].value;

						if (productId == "" || parentId == "" || content == "") {
							alert("Dữ liệu không được để trống");
							return null;
						}
						$http
								.get(
										'comment/save?accountId=' + accId
												+ '&productId=' + productId
												+ '&parentId=' + parentId
												+ '&content=' + content)
								.then(
										function(result) {
											if (result == null) {
												alert("Gửi lỗi. Vui lòng thử lại sau!");
											}
											document.getElementById("replyForm"
													+ i).style.display = "none";
											$scope.getcm(productId);
										});

					}

				});
