name := "scala-grpc"

version := "0.1"

scalaVersion := "2.12.8"

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)