(import model.*)

;(deftemplate Student  (declare (from-class Student)))
;(deftemplate Course   (declare (from-class Course)))
;(deftemplate Register (declare (from-class Register)))

(deftemplate Course (slot id) (slot section) 
  (multislot prerequisite (default nil)) (slot days)
  (slot start) (slot end))

(deftemplate Student (slot id) (slot name) 
  (multislot courseTaken (default nil))
  (multislot courseToTake (default nil)))

(deftemplate Register (slot sID) (slot cID) (slot secID))

(deftemplate pre_ok (declare (ordered TRUE)))

(defrule check_prerequisite
  "Check if all the prerequisite courses are taken."
  (Register (cID ?c))
  (Course (id ?c) (prerequisite ?p))
  (Student (courseTaken $? ?p $P))
  =>
  (assert (pre_ok yes))
  )

(defrule te
  =>
  (add (new Decision TRUE "Good"))
  )