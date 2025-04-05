<!DOCTYPE html>
<html>
<head>
    <title>Student Portal - Attendance Entry</title>
</head>
<body>
    <h2>Enter Attendance Details</h2>
    <form action="saveAttendance" method="post">
        Student Name: <input type="text" name="studentName" required><br><br>
        Student ID: <input type="text" name="studentId" required><br><br>
        Date: <input type="date" name="date" required><br><br>
        Status:
        <select name="status">
            <option value="Present">Present</option>
            <option value="Absent">Absent</option>
        </select><br><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
