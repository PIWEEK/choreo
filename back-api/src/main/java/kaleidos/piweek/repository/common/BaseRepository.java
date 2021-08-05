package kaleidos.piweek.repository.common;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/** Base repository to access {@link EntityManager} */
public class BaseRepository {
  @PersistenceContext
  private final transient EntityManager entityManager;

  /**
   * Initializes repository with {@link EntityManager}
   *
   * @param entityManager persistence {@link EntityManager} instance
   */
  public BaseRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  /**
   * Returns the current {@link EntityManager}
   *
   * @return an {@link EntityManager}
   */
  public EntityManager getEntityManager() {
    return entityManager;
  }
}
