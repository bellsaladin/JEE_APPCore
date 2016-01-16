package com.seosoft.erp.model.base;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.joda.time.DateTime;

import com.seosoft.erp.util.annotation.SortByInView;

/**
 * La classe Super de toutes les entities
 * 
 * @version 1.2
 */

@MappedSuperclass
public abstract class BaseEntity {

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||| Attributs à persister dans base de données ||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	/**
	 * Clé technique générée automatiquement pour toutes les classes modèles
	 */
	@Id
	@Length(max = 15, min=5)
	protected String id;

	/**
	 * Flag qui indique que l'objet nouvellement crée
	 */
	@NotNull
	protected boolean flagInsert;

	/**
	 * Flag qui indique que l'objet est modifié
	 */
	@NotNull
	protected boolean flagUpdate;

	/**
	 * Date de la dernière modification d'un objet
	 */
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	protected DateTime lastUpdate;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||| Attributs transitoires ||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	/**
	 * transitoire utiliser dans certains traitement utile pour flaguer l'objet en cours
	 */
	@Transient
	protected boolean selected;

	/**
	 * transitoire utiliser pour identifier, de manière unique, un objet non encore persisté dans la base de données
	 */
	@Transient
	private final String uuid;

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur |||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public BaseEntity() {
		super();
		uuid = UUID.randomUUID().toString();
	}

	public BaseEntity(String id) {
		super();
		uuid = UUID.randomUUID().toString();
		this.id = id;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	/**
	 * Text générique à redifinir dans chaque classe fille Pour afficher le libellé de l'objet
	 * 
	 * @return un String
	 */
	@Transient
	public abstract String getDisplayText();

	/**
	 * Permet de redéfinir la méthode toString pour afficher le libelle de l'objet en se basant sur le méthode getDisplayText()
	 */
	@Override
	public String toString() {
		return getDisplayText();
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||| Méthodes déclenchées après événement ||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	/**
	 * Méthode déclenchée avant qu'un persist soit fait
	 */
	@PrePersist
	public void prePersist() {
		lastUpdate = new DateTime();
		flagInsert = true;
	}

	/**
	 * Méthode déclenchée avant qu'un update soit fait
	 */
	@PreUpdate
	public void preUpdate() {
		lastUpdate = new DateTime();
		flagUpdate = true;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||| Méthodes equals & hashCode ||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	/**
	 * Permet de générer une valeur (32bit) hachage
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * Permet de comparer la similarité de deux objets
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			} else {
				return (uuid.equals(other.uuid));
			}
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| SPECIAL FUNCTIONS ||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	
	public  List<String> getFieldsNames(){
		return getFieldsNames("all");
	}
	public  List<String> getFieldsNames(String filter){
		List<String> fieldsList = new ArrayList<String>();
		fieldsList.add("id"); // add id to list because it can't be read at super class
		System.out.println("Filter" + filter);
		Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
        	if( !field.isAccessible()) continue;
        	Annotation[] annotations = field.getDeclaredAnnotations();
        	
        	System.out.printf("%s %s %s%n",
                    Modifier.toString(field.getModifiers()),
                    field.getType().getSimpleName(),
                    field.getName()
                );
        	
        	if(filter.equals("sortByInView")){
        		System.out.println("FilterINNNN : " + annotations.length);
            	for(Annotation annotation: annotations){
            		System.out.printf("Annotation %s %n",
                            annotation.annotationType().toString()
                        );
            		if(annotation.annotationType().equals(SortByInView.class))
        		        fieldsList.add(field.getName());
            	}
    		}else if(filter.equals("all")){
    			fieldsList.add(field.getName());
    		}
        }
        
        return fieldsList;
	}
	

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	// |||||||||||||||||||||||||||||||||||||||||||||||| Getters & Setters ||||||||||||||||||||||||||||||||||||||||||||||||||||||//
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isSelected() {
		return selected;
	}

	// TODO REMOVE AFTER TESTING
	public DateTime getUpdateDate() {
		return lastUpdate;
	}

	// TODO REMOVE AFTER TESTING
	public void setUpdateDate(DateTime updateDate) {
		this.lastUpdate = updateDate;
	}
	

}