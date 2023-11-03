<%--
  Created by IntelliJ IDEA.
  User: Игорь
  Date: 27.10.2023
  Time: 8:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="/templates/head.jsp" />
</head>
<style>
    #map {
        height: 400px;
        width: 100%;
    }
    .error{
        color: red;
    }
</style>
<body>
<jsp:include page="/templates/header.jsp" />
<form action="OrderAddressServlet" method="post">
<c:forEach items="${selectedIngredients}" var="ingredient">
    <input type="hidden" name="selectedIngredients" value="${ingredient}">
</c:forEach>
        <input type="hidden" name="id" value="${id}">
        <input type="hidden" id="lon" name="lon" value="">
        <input type="hidden" id="lat" name="lat" value="">

    <div class="mb-3">
        <label for="firstname" class="form-label">Enter your firstname:</label>
        <input id="firstname" class="form-control" name="firstname" type="text" required></div>
    <c:if test="${not empty firstnameError}">
        <div class="error">
                ${firstnameError}
        </div>
    </c:if>
    </div>
    <div class="mb-3">
        <label for="telephoneNumber" class="form-label">Enter your telephone number:</label>
        <input id="telephoneNumber" class="form-control"  name="telephoneNumber" type="text" required></div>
    <c:if test="${not empty telephoneError}">
        <div class="error">
                ${telephoneError}
        </div>
    </c:if>
    </div>
    <div class="mb-3">
        <label for="address" class="form-label">Enter your address:</label>
        <input id="address" class="form-control" name="locationName"  type="text"></div>
    <c:if test="${not empty latError}">
        <div class="error">
                ${latError}
        </div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="error">
                ${error}
        </div>
    </c:if>
    </div>
    <div class="mb-3">
        <label for="email" class="form-label">Enter your email:</label>
        <input id="email" class="form-control"  name="email" type="text" required></div>
    <c:if test="${not empty emailError}">
        <div class="error">
                ${latError}
        </div>
    </c:if>
    </div>
    <input class="btn btn-outline-primary" type="submit" value="Confirm order">
</form>
<div id="map"></div>
<jsp:include page="/templates/footer.jsp" />
<script
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCSq2S2sfE2E9ZeC0qGAmBhw4ScmnitED4&callback=initMap">
</script>
<script>
    let map;
    let geocoder = new google.maps.Geocoder();
    let marker = null;

    async function fetchPosition() {
        try {
            const response = await fetch("http://localhost:8080/FourteenHW_war_exploded/ShopPositionServlet");

            const data = await response.json();
            return data;

        } catch (error) {
            console.error(error);
        }
    }

    async function initMap() {
        const position = await fetchPosition();
        let lon = document.getElementById("lon");
        let lat = document.getElementById("lat");
        map = new google.maps.Map(document.getElementById("map"), {
            center: position,
            zoom: 16,
        });
        setCustomMarker(position); // shop, can be loaded from API
        map.addListener("click", function (event) {
            let latLng = event.latLng;
            if (marker)
                marker.setMap(null);

           lon.value = latLng.lng();
           lat.value = latLng.lat();

            marker = setMarker(latLng);

            getAddressFromLatLng(latLng);
        });

        function setMarker(latLng){
            const newMarker = new google.maps.Marker({
                map: map,
                position: latLng,
                title: 'Uluru',
            });
            return newMarker;
        }
        function setCustomMarker(latLng){
            const customIcon = {
                url: 'https://cdn.icon-icons.com/icons2/1465/PNG/512/543pizza1_100912.png',
                scaledSize: new google.maps.Size(40, 40)
            };
            const newMarker = new google.maps.Marker({
                map: map,
                position: latLng,
                title: 'Uluru',
                icon: customIcon
            });
        }
        function getAddressFromLatLng(latLng) {

            geocoder.geocode({ location: latLng }, function (results, status) {
                if (status === "OK" && results[0]) {
                    let address = results[0].formatted_address;

                    document.getElementById("address").value = address;
                }
            });
        }
    }

    initMap();
</script>

</body>
</html>
