/**
 * Copyright (C) 2009-2010 Scalable Solutions AB <http://scalablesolutions.se>
 */

package se.scalablesolutions.akka.persistence.common

import org.scalatest.matchers.ShouldMatchers
import se.scalablesolutions.akka.util.Logging
import org.scalatest.{BeforeAndAfterEach, Spec}

/**
 * Implementation Compatibility test for PersistentRef backend implementations.
 */

trait RefStorageBackendTest extends Spec with ShouldMatchers with BeforeAndAfterEach with Logging {
  def storage: RefStorageBackend[Array[Byte]]

  def dropRefs: Unit

  override def beforeEach = {
    log.info("beforeEach: dropping refs")
    dropRefs
  }

  override def afterEach = {
    log.info("afterEach: dropping refs")
    dropRefs
  }


  describe("A Properly functioning RefStorageBackend") {
    it("should successfully insert ref storage") {
      val name = "RefStorageTest #1"
      val value = name.getBytes
      storage.insertRefStorageFor(name, value)
      storage.getRefStorageFor(name).get should be(value)
    }

    it("should return None when getRefStorage is called when no value has been inserted") {
      val name = "RefStorageTest #2"
      val value = name.getBytes
      storage.getRefStorageFor(name) should be(None)
    }
  }

}