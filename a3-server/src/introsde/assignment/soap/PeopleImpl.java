package introsde.assignment.soap;
import introsde.document.model.HealthMeasureHistory;
import introsde.document.model.LifeStatus;
import introsde.document.model.MeasureDefinition;
import introsde.document.model.Person;

import java.util.Iterator;
import java.util.List;

import javax.jws.WebService;

//Service Implementation

@WebService(endpointInterface = "introsde.assignment.soap.People",
    serviceName="PeopleService")
public class PeopleImpl implements People {

    @Override
    public Person readPerson(int id) {
        System.out.println("---> Reading Person by id = "+id);
        Person p = Person.getPersonById(id);
        if (p!=null) {
            System.out.println("---> Found Person by id = "+id+" => "+p.getName());
        } else {
            System.out.println("---> Didn't find any Person with  id = "+id);
        }
        return p;
    }

    @Override
    public List<Person> readPersonList() {
        return Person.getAll();
    }

    @Override
    public int createPerson(Person person) {
        Person.savePerson(person);
        return person.getIdPerson();
    }

    @Override
    public int updatePerson(Person person) {
        Person.updatePerson(person);
        return person.getIdPerson();
    }

    @Override
    public int deletePerson(int id) {
        Person p = Person.getPersonById(id);
        if (p!=null) {
            Person.removePerson(p);
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public int updatePersonHP(int id, LifeStatus hp) {
        LifeStatus ls = LifeStatus.getLifeStatusById(hp.getIdMeasure());
        if (ls.getPerson().getIdPerson() == id) {
            LifeStatus.updateLifeStatus(hp);
            return hp.getIdMeasure();
        } else {
            return -1;
        }
    }

	@Override
	public List<HealthMeasureHistory> readPersonHistory(int id, String measureType) {
		int measureId = MeasureDefinition.getMeasureDefinitionIdByName(measureType).getIdMeasureDef();
		return (List<HealthMeasureHistory>) HealthMeasureHistory.getPersonHealthMeasureHistoryByMeasureType(id, measureId);		
	}

	@Override
	public LifeStatus readPersonMeasurement(Long id, String measureType, Long mid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int savePersonMeasurement(Long id, LifeStatus m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MeasureDefinition> readMeasureTypes() {
		// TODO Auto-generated method stub
		return null;
	}

}