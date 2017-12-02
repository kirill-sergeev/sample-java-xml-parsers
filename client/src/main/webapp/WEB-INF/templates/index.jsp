<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Booking Service</title>
</head>
<body>

<div>
  <p>${ticket.id}</p>
  <p>${ticket.bookDate}</p>
  <p>${ticket.seat}</p>
  <p>${ticket.serviceType}</p>
  <p>${ticket.airline}</p>
  <p>${ticket.flight}</p>
  <p>${ticket.passenger}</p>
  <p>${ticket.payment}</p>
</div>

</body>
</html>
