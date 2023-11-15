package springboot.mysql.aws.example.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import springboot.mysql.aws.example.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
