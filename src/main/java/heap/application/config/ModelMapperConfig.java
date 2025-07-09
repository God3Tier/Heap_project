package heap.application.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.record.RecordModule;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import heap.application.dto.ReviewDTO;
import heap.application.review.Review;
import heap.application.stalls.Stall;
import heap.application.stalls.StallRepo;
import heap.application.user.User;
import heap.application.user.UserRepo;

@Component
public class ModelMapperConfig {
    @Autowired
    private StallRepo stallRepo;
    @Autowired
    private UserRepo userRepo;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper().registerModule(new RecordModule());
        Converter<Integer, Stall> stallIdconverter = ctx -> {
            Integer id = ctx.getSource();
            return (id == null) ? null : stallRepo.findById(id).orElse(null);
        };

        Converter<Integer, User> userIdConverter = ctx -> {
            Integer id = ctx.getSource();
            return (id == null) ? null : userRepo.findById(id).orElse(null);
        };

        // Converter<Integer, 
        modelMapper.getConfiguration()
                   .setFieldMatchingEnabled(true)
                   .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        modelMapper.typeMap(ReviewDTO.class, Review.class)
            .addMappings(mapper -> {
                mapper.using(stallIdconverter)
                        .map(ReviewDTO::getStallId, Review::setStall);
                mapper.using(userIdConverter)
                        .map(ReviewDTO::getUserId, Review::setUser);
            });
        return modelMapper;

    }
}
