package dmitriytek.chatik.repo;

import dmitriytek.chatik.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
